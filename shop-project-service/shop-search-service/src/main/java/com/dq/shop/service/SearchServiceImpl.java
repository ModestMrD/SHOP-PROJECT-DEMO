package com.dq.shop.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.dq.common.POJO.PageInfo;
import com.dq.common.POJO.ResultBean;
import com.dq.project.mapper.TProductMapper;
import com.dq.shop.ISearchService;
import com.project.entity.TProduct;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author DuQian
 * @Date 2019/3/14
 */
@Component
@Service
public class SearchServiceImpl implements ISearchService{

    @Autowired
    private TProductMapper productMapper;

    @Autowired
    private SolrClient solrClient;

    @Override
    public PageInfo page(String keyWord, Integer indexNum, Integer pageSize) {
        PageInfo<TProduct> pageInfo = new PageInfo<>();
        //1.组装查询条件
        SolrQuery queryCondition = new SolrQuery();
        if(!StringUtils.isAllEmpty(keyWord)){
            queryCondition.setQuery("product_keywords:"+keyWord);
        }else{
            queryCondition.setQuery("product_keywords:华为");
        }
        //2.增加一个高亮的效果
        queryCondition.setHighlight(true);
        queryCondition.addHighlightField("product_name");
        queryCondition.addHighlightField("product_sale_point");
        queryCondition.setHighlightSimplePre("<font color='red'>");
        queryCondition.setHighlightSimplePost("</font>");

        //3.增加分页
        queryCondition.setStart((indexNum-1)*pageSize);
        queryCondition.setRows(pageSize);
        List<TProduct> results = null;
        long totalCount = 0L;
        try {
            //2.执行查询
            QueryResponse response = solrClient.query(queryCondition);
            SolrDocumentList list = response.getResults();
            totalCount = list.getNumFound();
            //获取到高亮的信息
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            results = new ArrayList<>(list.size());
            //3.将查询结果List<Docuemnt>转换为List<TProduct>
            for (SolrDocument document : list) {
                //document->product
                TProduct product = new TProduct();
                product.setId(Long.parseLong(document.getFieldValue("id").toString()));
                //product.setName(document.getFieldValue("product_name").toString());
                product.setImage(document.getFieldValue("product_image").toString());
                product.setPrice(Long.parseLong(document.get("product_price").toString()));
                //product.setSalePoint(document.getFieldValue("product_sale_point").toString());
                //获取商品名称的高亮信息
                //1，获取到当前这条记录的高亮信息
                Map<String, List<String>> map = highlighting.get(document.getFieldValue("id").toString());
                //2，获取商品名称的字段的高亮信息
                List<String> productNameHighlight = map.get("product_name");
                //3.单独处理高亮的设置
                if(productNameHighlight!= null && productNameHighlight.size() > 0){
                    //如果本次是按照商品名称搜索到的记录
                    product.setName(productNameHighlight.get(0));
                }else{
                    product.setName(document.getFieldValue("product_name").toString());
                }
                //获取商品卖点的高亮信息
                List<String> productSalePointHighlight = map.get("product_sale_point");
                if(productSalePointHighlight != null && productSalePointHighlight.size() > 0){
                    //按照商品卖点搜索到的记录
                    product.setSalePoint(productSalePointHighlight.get(0));
                }else{
                    product.setSalePoint(document.getFieldValue("product_sale_point").toString());
                }
                results.add(product);
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pageInfo.setPageNum(indexNum);
        pageInfo.setPageSize(pageSize);
        pageInfo.setList(results);
        pageInfo.setTotal(totalCount);
        pageInfo.setPages((int) (totalCount%pageSize==0?(totalCount/pageSize):(totalCount/pageSize)+1));
        return pageInfo;
    }

    @Override
    public ResultBean initAllData() {
        List<TProduct> list = productMapper.list();
        for (TProduct product : list) {
            SolrInputDocument document = new SolrInputDocument();
            document.setField("id",product.getId());
            document.setField("product_name",product.getName());
            document.setField("product_image",product.getImage());
            document.setField("product_sale_point",product.getSalePoint());
            document.setField("product_price",product.getPrice());
            try {
                solrClient.add(document);
            } catch (Exception e) {
                e.printStackTrace();
                ResultBean.error("添加到索引库失败");
            }
        }
        return ResultBean.success("添加到索引库成功");
    }

    @Override
    public ResultBean updateData(TProduct product) {
        SolrInputDocument document = new SolrInputDocument();
        document.setField("id",product.getId());
        document.setField("product_name",product.getName());
        document.setField("product_image",product.getImage());
        document.setField("product_sale_point",product.getSalePoint());
        document.setField("product_price",product.getPrice());
        try {
            solrClient.add(document);
        } catch (Exception e) {
            e.printStackTrace();
            ResultBean.error("更新索引库失败");
        }
        return ResultBean.success("更新索引库成功");
    }
}
