package query;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jet on 2017/7/29.
 */
/*
FilteredQuery

FilteredQuery包含两个成员变量：
    Query query：查询对象
    Filter filter：其有一个函数DocIdSet getDocIdSet(IndexReader reader) 得到一个文档号集合，
    结果文档必须出自此文档集合，注此处的过滤器所包含的文档号并不是要过滤掉的文档号，而是过滤后需要的文档号。

TermsFilter其包含一个成员变量Set<Term> terms=new TreeSet<Term>()，
所有包含terms集合中任一term的文档全部属于文档号集合。

BooleanFilter:
其像BooleanQuery相似，包含should的filter，must的filter，not的filter，在getDocIdSet的时候，
先将所有满足should的文档号集合之间取OR的关系，然后同not的文档号集合取NOT的关系，最后同must的文档号集合取AND的关系，得到最后的文档集合。



FieldCacheRangeFilter<T>及FieldCacheTermsFilter:
在介绍与FieldCache相关的Filter之前，先介绍FieldCache。
FieldCache缓存的是不是存储域的内容，而是索引域中term的内容，索引中的term是String的类型，
然而可以将其他的类型作为String类型索引进去，例如"1"，"2.3"等，然后搜索的时候将这些信息取出来。
 */

public class FilterQueryTest {

    private List<Filter> filterList;
    public FilterQueryTest(){
        filterList = new ArrayList<Filter>();
    }
    public void addFilter(String Field,String Value){
        Term term=new Term(Field,Value);//添加term
        QueryWrapperFilter filter=new QueryWrapperFilter(new TermQuery(term));//添加过滤器
        filterList.add(filter);//加入List，可以增加多個过滤
    }
    public Query getFilterQuery(Query query){
        for(int i=0;i<filterList.size();i++){
            //取出多個过滤器，在结果中再次定位结果
            query = new FilteredQuery(query, filterList.get(i));
        }
        return query;
    }

    public static void main(String[] args) throws IOException {

        Directory directory = FSDirectory.open(new File("index"));
        //创建IndexReader
        DirectoryReader directoryReader  = DirectoryReader.open(directory);
        //根据IndexReader创建IndexSearch
        IndexSearcher searcher = new IndexSearcher(directoryReader);
        FilterQueryTest test = new FilterQueryTest();
        test.addFilter("content","美");
        test.addFilter("content","三");
        Query query = new TermQuery(new Term("content","美"));
        Query query2 = new TermQuery(new Term("content","韩"));
        QueryWrapperFilter wrapperFilter = new QueryWrapperFilter(query);
        Query query1=test.getFilterQuery(query);//结果过滤
       TotalHitCountCollector countCollector = new TotalHitCountCollector();
        Query query3 = new MatchAllDocsQuery();
        searcher.search(query2,countCollector);
        System.out.println("总记录数"+countCollector.getTotalHits());

        TopDocs hits=searcher.search(query3,1);
        for (ScoreDoc doc : hits.scoreDocs) {
            Document ldoc = directoryReader.document(doc.doc);
//            String title = ldoc.get("teacherid");
            System.out.println("分数： "+doc.score+" 内容： "+ldoc.get("content"));
        }
    }

}
