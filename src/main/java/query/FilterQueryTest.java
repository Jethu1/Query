package query;

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



}
