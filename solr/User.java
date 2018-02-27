package demo.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.Field;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.data.annotation.Id;

import java.io.IOException;
import java.util.List;

/**
 * Created by Lee on 2018/2/24.
 */
public class User {
    @Id
    @Field
    private String id;
    @Field
    private String name;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + "]";
    }

    public static void main(String[] args) {
        User user = new User();
        user.setId("123456");
        user.setName("程高伟");
        //saveSolrResource(user);
        SolrQuery query = new SolrQuery();
        query.setQuery("程高伟");
        String url="http://localhost:8983/solr";
        SolrClient client=new HttpSolrClient.Builder(url).build();
        QueryResponse rsp = null;
        try {
            rsp = client.query("demo",new SolrQuery("*:*"));
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<User> userList = rsp.getBeans(User.class);
        System.out.println(userList);
    }
}
