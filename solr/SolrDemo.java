package demo.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrResponse;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by Lee on 2018/2/24.
 */
public class SolrDemo {
    private static SolrClient client;
    private static String url;
    static {
        url = "http://localhost:8983/solr/demo";
        client = new HttpSolrClient.Builder(url).build();
    }

    public static boolean saveSolrResource(Article article){
        DocumentObjectBinder binder = new DocumentObjectBinder();
        SolrInputDocument doc=binder.toSolrInputDocument(article);

        try {
            client.add(doc);
            System.out.println(client.commit());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    public static void main(String[] args) {
        Article article = new Article();
        article.setId("123456");
        article.setTitle(new String[] {"测试solr"});
        article.setAuthor(new String[]{"程高伟"});
        article.setTime(new Date[]{new Date()});
        article.setInfo(new String[]{"The Files screen lets you browse & view the various configuration files"});
        saveSolrResource(article);

        QueryResponse response=null;
        try {
            response =client.query(new SolrQuery("*:*"));
            List<Article> articleList = response.getBeans(Article.class);
            System.out.println(articleList);

            SolrDocument resp= client.getById("123456");
            System.out.println(resp.toString());

            response =client.query(new SolrQuery("lee"));
            articleList = response.getBeans(Article.class);
            System.out.println(articleList);

            client.deleteByQuery("*:*");
            client.commit();

            //client.deleteById("123456");
            //client.commit();
            response =client.query(new SolrQuery("*:*"));
            articleList = response.getBeans(Article.class);
            System.out.println(articleList);

        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }





    }
}
