ES基本概念
ES架构
ES应用场景
ES安装和使用
Kibana安装和使用
Java客户端和Spring Data ElasticSearch
ES索引、映射、文档的基本操作
ES的搜索功能
ES文本搜索的原理和使用
分词器的原理和使用
中文搜索、同义词、停用词、高亮显示搜索功能
ES搜索排序原理和使用
打分具体方式及用户自定义打分的相关功能
聚合场景、聚合指标、桶聚合、聚合方式

Lucene 仅提供了基础的搜索引擎支持，而对搜索的分布式、容错性、实时性并不支持
ES是建立在Lucene基础之上的分布式准实时搜索引擎
索引、文档、字段、映射（建立索引时需要定义文档的数据结构）
ES动态映射功能
ES集群、节点Node
索引的分片个数只能设置一次，不可以修改。ES7之前一个主分片默认有5个副本分片，ES7开始一个主分片默认一个副本分片
一个分片的主分片和副本分片分别存储在不同计算机上
DSL domain specific language 领域特定语言  JSON形式
ES使用倒排索引
ES不支持事务，ES更新文档时，先读取文档再进行修改，然后再为文档重新建立索引
ES使用了乐观锁，版本控制
集群中节点有哪些角色？ES内部如何进行工作？ES是如何提升数据的高可用性？
节点分类：
master节点：
维护集群相关工作，集群变更、索引创建、删除、节点健康状态监控、节点上线和下线。通过选举产生，只有一个master节点
数据节点：负责索引数据的保存，文档的操作。底层调用Lucene进行操作，对于内存和IO的消耗较大
协调节点：客户端可以向ES集群节点发起请求，该节点为协调节点。可以专门设置协调节点
（中有一份分片-节点路由表，存放分片和节点的对应关系，轮询算法）
ES提高服务的高并发性能，增加索引的副本分片个数
协调节点根据数据获取分片ID的计算公式：shard = hash(routing) % number_of_primary_shards
routing：每条文档提交时的参数，可变，默认情况下使用的是文档的id值
number_of_primary_shards：索引中主分片的个数
文档写入：
当ES协调节点收到来自对某索引的写入请求时，协调节点会根据路由算法将该文档映射到某个主分片上，然后将请求转发到该分片所在的节点。完成数据存储后，
该节点会将请求转发给该分片的其他副本分片所在的节点，直到所有副本分片节点全部写入完成，ES协调节点向客户端报告写入成功
ES使用场景
搜索引擎：垂直搜索
第三方数据同步模块负责将数据库中的数据按照业务需要同步到ES中
推荐系统：7.0及以上引入高纬向量的数据类型
二级索引：主键存在RDBMS中，为一级索引；ES中的查询字段构成的索引是二级索引

映射中的字段类型是不可以修改的，但是字段是可以扩展的，常见是增加字段和object类型的数据新增属性

基本数据类型：
keyword
该类型是不进行切分的字符串类型，在索引时，对keyword类型的数据不进行切分，直接构建倒排索引；在搜索时，对该类型的查询字符串不进行
切分后的部分匹配
一般用于对文档的过滤、排序和聚合
text
可以进行切分的字符串类型。在索引时，可按照相应的切词算法对文本内容进行切分，然后构建倒排索引；在搜索时，对该类型的查询字符串
按照用户的分词算法进行切分，然后对切分后的部分匹配打分
boolean
一般用于业务中的二值表示，true或者false 
date
ES中存储的日期时标准的UTC格式。
格式化的日期字符串
毫秒级的长整型，从1970年1月1日0点到现在的毫秒数
秒级别的整形，从1970年1月1日0点到现在的秒数
日期类型默认格式strict_date_optional_time||epoch_millis，其中strict_date_optional_time的含义是严格的时间类型，支持
yyyy-MM-dd、yyyyMMdd、yyyyMMddHHmmss、yyyy-MM-ddTHH:mm:ss、yyyy-MM-ddTHH:mm:ss.SSS、
yyyy-MM-ddTHH:mm:ss.SSSZ等格式
如果需要支持yyyy-MM-dd HH:mm:ss格式，需要设置type为date的format为yyyy-MM-dd HH:mm:ss
动态映射：字段没有定义时，ES可以根据写入的数据自动定义该字段类型。
bool查询 
是一个复合查询，多个子查询组合成一个布尔表达式。子查询之间的逻辑关系是与
must 必须匹配该查询条件
should 可以匹配该查询条件
must_not 必须不匹配该查询条件
filter 必须匹配过滤条件，不进行打分计算
match搜索
```
GET /hotel_order/_search
{
  "query": {
    "match": {
      "user_name": {
        "query": "Michael Jordan",
        "operator": "and",
        "minimum_should_match": "80%"  // 最小匹配参数
      }
    }
  }
}
```
multi_match查询
fields子句中指定需要搜索的字段列表

match_phrase查询
用于匹配短语，用于搜索确切的短语或邻近的词语，slop 最大匹配距离之间为2

搜索建议
在用户输入搜索关键词的过程中系统进行自动补全，用户可以根据自己的需求单击搜索建议的内容直接进行搜索。
Completion Suggester，对应的字段类型需要定义为completion类型
使用的索引结构不是倒排索引，而是在内存中构建FST（finite stateTransducers）。构建该数据结构有较大的内存存储成本，因此在生产
环境中向索引中添加数据时一定要关注ES节点的内存消耗，避免数据量过大造成ES节点内存耗尽从而影响集群服务
在默认情况下ES查询时使用sort对结果排序是不计算分数的

ES在文本索引的建立和搜索过程中依赖两大组件，即Lucene和分词器
Lucene负责进行倒排索引的物理构建
分词器负责在建立倒排索引前和搜索前对文本进行分词和语法处理。
ES将文档交给分词器处理，处理过程包括字符过滤、分词、分词过滤
词语矩阵----词语和文档的包含关系
词典：term dictionary ES会遍历文档词语矩阵中的每一个词语，然后将包含该词语的文档信息与该词语建立一种映射关系
文本搜索过程
1、ES将查询的字符串传入对应的分词器中，分词器对查询文本进行分词，并把分词后的每隔词语变换为对应的底层lucene term查询
2、ES用term查询在倒排索引中查找每个term，然后获取一组包含该term的文档集合
3、ES根据文本相关度对每个文档进行打分计算，打分完毕后，ES把文档按照相关性进行倒序排序
4、ES根据得分高低返回匹配的文档
分词器应用场景：
1、创建或更新文档时，对应的文本字段进行分词处理
2、查询文本字段时，对查询语句进行分词
分词器结构遵循的三段式原则：
1、字符过滤器，0个或多个
映射关系字符过滤器：根据配置的映射关系替换字符
HTML擦除过滤器：去掉HTML元素
正则表达式替换过滤器：用正则表达式处理字符串
2、分词器，必须只有一个
标准分词器：对英文分词时，基于语法分词；对中文分词时，切分成单字.
字母分词器：使用非字母的字符作为分词标记
小写分词器：等同字母分词器，并把所有分词结果转换为小写形式
空格分词器：使用空格作为分词标记
3、词语过滤器，0个或者多个
lower case过滤器：所有字母转换为小写形式
stop token过滤器：停用词从分词结果中移除
同义词分词过滤器：为分词结果添加同义词

内置分析器
1、standard分析器构成
standard分词器
lowercase分词过滤器
stop token分词过滤器
2、simple分析器 按非字母字符进行词语拆分，将所有词语转为小写
3、language分析器 语言分析器
4、whitespace分析器 按照空白字符拆分词语
5、pattern分析器 使用正则表达式将文本拆分为词语

索引时使用分析器
1、在索引的settings参数中设置当前索引的所有文本字段的分析器
2、在索引的mappings参数中设置当前字段的分词器

在默认情况下，ES对文本进行搜索时使用的分析器和索引时使用的分析器保持一致

中文分词：
1、基于词典的分词算法
词典和待匹配的字符串进行匹配，匹配到词典中某个词时，说明该词分词成功
正向最大化匹配算法、逆向最大化匹配算法、双向最大化匹配算法
2、基于统计的机器学习算法
事先构建一个语料库，时标记好的分词形式的语料，然后统计每个词出现的频率或词间共现频率
HMM、CRF、深度学习

插件：HanLP和IK分词器

IK分词器
https://github.com/medcl/elasticsearch-analysis-ik
在ES/plugins目录下新建目录ik-analysis
下载插件，插件版本需要和当前ES版本保持一致
重启ES
ik_smart：切分粒度比较粗
ik_max_word：将文本进行了最细粒度的拆分

添加词典,ik分词器目录config子目录下创建my.dict，每隔词语单独一行
修改ik分词器的配置文件，config/IKAnalyzer.cfg.xml，将新建的字典文件加入ext_dict选项中,重启ES。
在默认情况下，IK分析器的分词器只有英文停用词，没有中文停用词
自定义停用词：
/plugins/ik-analysis/config目录下创建my_stopword.dict
修改${ES_HOME}/plugins/ik-analysis/config/IKAnalyzer.cfg.xml文件，设置配置项ext_stopwords的值为停用词词典的文件名称

拼音分词器：
https://github.com/medcl/elasticsearch-analysis-pinyin

举例：
```
PUT /hotel5 
{
  "settings": {
    "analysis": {
      "analyzer": {
        "ik_pinyin_analyzer":{
          "tokenizer":"ik_max_word",
          "filter":["pinyin_filter"]
        }
      },
      "filter": {
        "pinyin_filter":{
          "type":"pinyin",
          "keep_first_letter":true, // 这是保留拼音的首字母
          "keep_full_pinyin":false,// 设置保留拼音的全拼
          "keep_none_chinese":true // 设置不保留中文
        }
      }
    }
  },
  "mappings": {
    "properties": {
      "title":{
        "type": "text",
        "analyzer": "ik_pinyin_analyzer"
      }
    }
  }
}
```

拼写纠错 fuzzy-match搜索
使用编辑距离和倒排索引结合的形式完成纠错

高亮显示搜索：
highlight
默认使用```<em>```
ES支持的高亮显示搜索策略有plain、unified、fvh，用户可以根据搜索场景进行选择
plain精准度比较高的策略，必须将文档全部加载到内存中，并重新执行查询分析。适合在单个字段上进行简单的高亮显示搜索。
unified策略是由lucene unified highlighter来实现，使用BM25算法进行匹配，默认情况下，ES高亮显示使用的是该策略
fvh是基于向量的高亮显示搜索策略 fast vector highlighter。更适合在文档中包含大字段的情况下使用。
举例：
```
GET /hotel_order/_search
{
  "query": {
    "match": {
      "user_name": "Jordan"
    }
  },
  "highlight": {
    "fields": {
      "user_name": {}
    }
  }
}
```
响应：
```json
{
  "took" : 1,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : {
      "value" : 4,
      "relation" : "eq"
    },
    "max_score" : 0.10536051,
    "hits" : [
      {
        "_index" : "hotel_order",
        "_type" : "_doc",
        "_id" : "001",
        "_score" : 0.10536051,
        "_source" : {
          "order_id" : "001",
          "user_id" : "user_00x",
          "user_name" : "Michael Jordan",
          "hotel_id" : "h001"
        },
        "highlight" : {
          "user_name" : [
            "Michael <em>Jordan</em>"
          ]
        }
      },
      {
        "_index" : "hotel_order",
        "_type" : "_doc",
        "_id" : "002",
        "_score" : 0.10536051,
        "_source" : {
          "order_id" : "002",
          "user_id" : "user_00a",
          "user_name" : "Stephen Jordan",
          "hotel_id" : "h0500"
        },
        "highlight" : {
          "user_name" : [
            "Stephen <em>Jordan</em>"
          ]
        }
      },
      {
        "_index" : "hotel_order",
        "_type" : "_doc",
        "_id" : "003",
        "_score" : 0.10536051,
        "_source" : {
          "order_id" : "003",
          "user_id" : "user_30e",
          "user_name" : "Tim Jordan",
          "hotel_id" : "h0520"
        },
        "highlight" : {
          "user_name" : [
            "Tim <em>Jordan</em>"
          ]
        }
      },
      {
        "_index" : "hotel_order",
        "_type" : "_doc",
        "_id" : "004",
        "_score" : 0.10536051,
        "_source" : {
          "order_id" : "004",
          "user_id" : "user_430",
          "user_name" : "Kobe Jordan",
          "hotel_id" : "h0600"
        },
        "highlight" : {
          "user_name" : [
            "Kobe <em>Jordan</em>"
          ]
        }
      }
    ]
  }
}
```
请求：
```
GET /hotel_order/_search
{
  "query": {
    "match": {
      "user_name": "Jordan"
    }
  },
  "highlight": {
    "fields": {
      "user_name": {
        "pre_tags": "<high>",
        "post_tags": "</high>"
      }
    }
  }
}
```
响应：
```json
{
  "took" : 1,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : {
      "value" : 4,
      "relation" : "eq"
    },
    "max_score" : 0.10536051,
    "hits" : [
      {
        "_index" : "hotel_order",
        "_type" : "_doc",
        "_id" : "001",
        "_score" : 0.10536051,
        "_source" : {
          "order_id" : "001",
          "user_id" : "user_00x",
          "user_name" : "Michael Jordan",
          "hotel_id" : "h001"
        },
        "highlight" : {
          "user_name" : [
            "Michael <high>Jordan</high>"
          ]
        }
      },
      {
        "_index" : "hotel_order",
        "_type" : "_doc",
        "_id" : "002",
        "_score" : 0.10536051,
        "_source" : {
          "order_id" : "002",
          "user_id" : "user_00a",
          "user_name" : "Stephen Jordan",
          "hotel_id" : "h0500"
        },
        "highlight" : {
          "user_name" : [
            "Stephen <high>Jordan</high>"
          ]
        }
      },
      {
        "_index" : "hotel_order",
        "_type" : "_doc",
        "_id" : "003",
        "_score" : 0.10536051,
        "_source" : {
          "order_id" : "003",
          "user_id" : "user_30e",
          "user_name" : "Tim Jordan",
          "hotel_id" : "h0520"
        },
        "highlight" : {
          "user_name" : [
            "Tim <high>Jordan</high>"
          ]
        }
      },
      {
        "_index" : "hotel_order",
        "_type" : "_doc",
        "_id" : "004",
        "_score" : 0.10536051,
        "_source" : {
          "order_id" : "004",
          "user_id" : "user_430",
          "user_name" : "Kobe Jordan",
          "hotel_id" : "h0600"
        },
        "highlight" : {
          "user_name" : [
            "Kobe <high>Jordan</high>"
          ]
        }
      }
    ]
  }
}
```
停用词
常用的中文、英文停用词可以在https://www.ranks.nl/上提取
中文停用词地址为https://www.ranks.nl/stopwords/chinese-stopwords
英文停用词地址为https://www.ranks.nl/stopwords
```
PUT /hotel4
{
  "settings": {
    "analysis": {
      "filter": {
        "my_stop":{
          "type":"stop",
          "stopwords":[
            "我",
            "的",
            "这"
            ]
        }
      },
      "analyzer": {
        "standard_stop":{
          "tokenizer":"standard",
          "filter":["my_stop"]
        }
      }
    }
  },
  "mappings": {
    "properties": {
      "title":{
        "type": "text",
        "analyzer": "standard_stop"
      }
    }
  }
}
```
举例：
```
POST /hotel4/_analyze
{
  "field": "title",
  "text": ["我的酒店"]
}
```
响应:
```
{
  "tokens" : [
    {
      "token" : "酒",
      "start_offset" : 2,
      "end_offset" : 3,
      "type" : "<IDEOGRAPHIC>",
      "position" : 2
    },
    {
      "token" : "店",
      "start_offset" : 3,
      "end_offset" : 4,
      "type" : "<IDEOGRAPHIC>",
      "position" : 3
    }
  ]
}
```

同义词
使用场景：处理不同的查询词，有可能时表达相同搜索目标的场景。
使用方式：
1、建立索引时指定同义词并构建同义词的倒排索引
2、在搜索时指定字段的search_analyzer查询分析器使用同义词

使用synonyms或者synonym_graph

```
PUT /hotel3 
{
  "settings": {
    "analysis": {
      "filter": { // 定义分词过滤器
        "ik_synonyms_filter":{
          "type":"synonym",
          "synonyms":[ // 在分词过滤器中定义近义词
            "北京,首都",
            "天津,天津卫",
            "假日,度假"
            ]
        }
      },
      "analyzer": { // 自定义分词器
        "ik_analyzer_synonyms":{
          "tokenizer":"ik_max_word", // 指定分词器
          "filter":[ // 指定分词过滤器
            "lowercase",
            "ik_synonyms_filter"
            ]
        }
      }
    }
  },
  "mappings": {
    "properties": {
      "title":{
        "type": "text",
        "analyzer": "ik_analyzer_synonyms"
      }
    }
  }
}
```
```
PUT /hotel1 
{
  "settings": {
    "analysis": {
      "analyzer": {
        "default":{
          "type":"simple"
        }
      }
    }
  }
}

PUT /hotel1 
{
  "mappings": {
    "properties": {
      "title":{
        "type": "text",
        "analyzer": "whitespace"
      }
    }
  }
}
```
setting和mapping只能二选一设置分词器，否则会出现如下错误
```json
{
  "error" : {
    "root_cause" : [
      {
        "type" : "resource_already_exists_exception",
        "reason" : "index [hotel1/5vc-flK_Qom81sNdlg7Q5g] already exists",
        "index_uuid" : "5vc-flK_Qom81sNdlg7Q5g",
        "index" : "hotel1"
      }
    ],
    "type" : "resource_already_exists_exception",
    "reason" : "index [hotel1/5vc-flK_Qom81sNdlg7Q5g] already exists",
    "index_uuid" : "5vc-flK_Qom81sNdlg7Q5g",
    "index" : "hotel1"
  },
  "status" : 400
}
```

```
POST _analyze
{
"analyzer": "standard",
"text": ["The letter tokenizer is not configurable"]
}
```
结果：
```
{
  "tokens" : [
    {
      "token" : "the", // 切分后的第一个词语
      "start_offset" : 0,// 该词在文本中的起始偏移量
      "end_offset" : 3,// 该词在文本中的结束偏移量
      "type" : "<ALPHANUM>",// 词性
      "position" : 0// 该词语在原文本中是第0个出现的词语
    },
    {
      "token" : "letter",
      "start_offset" : 4,
      "end_offset" : 10,
      "type" : "<ALPHANUM>",
      "position" : 1
    },
    {
      "token" : "tokenizer",
      "start_offset" : 11,
      "end_offset" : 20,
      "type" : "<ALPHANUM>",
      "position" : 2
    },
    {
      "token" : "is",
      "start_offset" : 21,
      "end_offset" : 23,
      "type" : "<ALPHANUM>",
      "position" : 3
    },
    {
      "token" : "not",
      "start_offset" : 24,
      "end_offset" : 27,
      "type" : "<ALPHANUM>",
      "position" : 4
    },
    {
      "token" : "configurable",
      "start_offset" : 28,
      "end_offset" : 40,
      "type" : "<ALPHANUM>",
      "position" : 5
    }
  ]
}
```
自定义分析器：
```
PUT /hotel2
{
  "settings": {
    "analysis": {
      "analyzer": {
        "comma_analyzer":{ // 自定义分析器
          "tokenizer":"comma_tokenizer" // 使用comma_tokenizer分词器
        }
      },
      "tokenizer": { // 自定义分词器
        "comma_tokenizer":{
          "type":"pattern",
          "pattern":","// 切分时使用的分隔符
        }
      }
    }
  }
}
```
```
POST _analyze
{
  "tokenizer": "standard", // 使用standard分词器
  "filter": ["lowercase"], // 使用lowercase分词过滤器
  "text": ["The letter tokenizer is not configurable"]// 待分析的文本
}
```

```
GET /hotel_sug/_search
{
  "suggest": {
    "hotel_zh_sug": {
      "prefix": "如家",
      "completion": {
        "field": "query_word"
      }
    }
  }
}

PUT /hotel_sug
{
  "mappings": {
    "properties": {
      "query_word": {
        "type": "completion"
      }
    }
  }
}

POST /_bulk
{"index":{"_index":"hotel_sug","_id":"001"}}
{"query_word":"如家酒店"}
{"index":{"_index":"hotel_sug","_id":"002"}}
{"query_word":"如家快捷酒店"}
{"index":{"_index":"hotel_sug","_id":"003"}}
{"query_word":"如家精选酒店"}
{"index":{"_index":"hotel_sug","_id":"004"}}
{"query_word":"汉庭假日酒店"}
```

搜索响应：
```
{
  "took" : 947,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : {
      "value" : 0,
      "relation" : "eq"
    },
    "max_score" : null,
    "hits" : [ ]
  },
  "suggest" : {
    "hotel_zh_sug" : [
      {
        "text" : "如家",
        "offset" : 0,
        "length" : 2,
        "options" : [
          {
            "text" : "如家快捷酒店",
            "_index" : "hotel_sug",
            "_type" : "_doc",
            "_id" : "002",
            "_score" : 1.0,
            "_source" : {
              "query_word" : "如家快捷酒店"
            }
          },
          {
            "text" : "如家精选酒店",
            "_index" : "hotel_sug",
            "_type" : "_doc",
            "_id" : "003",
            "_score" : 1.0,
            "_source" : {
              "query_word" : "如家精选酒店"
            }
          },
          {
            "text" : "如家酒店",
            "_index" : "hotel_sug",
            "_type" : "_doc",
            "_id" : "001",
            "_score" : 1.0,
            "_source" : {
              "query_word" : "如家酒店"
            }
          }
        ]
      }
    ]
  }
}
```

映射关系举例：
```shell
curl -X PUT "localhost:9200/my_index?pretty" -H 'Content-Type: application/json' -d'
{
  "mappings": {
    "properties": {
      "full_name": {
        "type":  "text"
      }
    }
  }
}
'

```

创建文档举例：
```shell
curl -X PUT "localhost:9200/twitter/_doc/1?pretty" -H 'Content-Type: application/json' -d'
{
    "user" : "kimchy",
    "post_date" : "2009-11-15T14:12:12",
    "message" : "trying out Elasticsearch"
}
'

```

使用java client方式：
```xml
<dependencies>
    <dependency>
      <groupId>co.elastic.clients</groupId>
      <artifactId>elasticsearch-java</artifactId>
      <version>7.16.3</version>
    </dependency>
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-databind</artifactId>
      <version>2.12.3</version>
    </dependency>
  </dependencies>
```

使用spring data elasticsearch 
封装了创建客户端的逻辑、与服务端保持长连接
通过对repository接口的自动实现，通过方法名的语义实现查询功能。

默认情况下，当一个别名只指向一个索引时，写入数据的请求可以指向这个别名，如果别名指向多个索引，则写入数据的请求是不可以指向这个别名的

别名alias创建
```
PUT /january_log
{
  "mappings": {
    "properties": {
      "uid":{
        "type": "keyword"
      },
      "hotel_id":{
        "type": "keyword"
      },
      "check_in_date":{
        "type": "keyword"
      }
    }
  }
}

PUT /february_log
{
  "mappings": {
    "properties": {
      "uid":{
        "type": "keyword"
      },
      "hotel_id":{
        "type": "keyword"
      },
      "check_in_date":{
        "type": "keyword"
      }
    }
  }
}

PUT /march_log
{
  "mappings": {
    "properties": {
      "uid":{
        "type": "keyword"
      },
      "hotel_id":{
        "type": "keyword"
      },
      "check_in_date":{
        "type": "keyword"
      }
    }
  }
}
```
响应:
```
{
  "acknowledged" : true,
  "shards_acknowledged" : true,
  "index" : "march_log"
}
```

文档创建：
```
POST /january_log/_doc/001
{
  "uid":"001",
  "hotel_id":"92772",
  "check_in_date":"2021-01-05"
}


POST /february_log/_doc/001
{
  "uid":"001",
  "hotel_id":"33224",
  "check_in_date":"2021-02-23"
}


POST /march_log/_doc/001
{
  "uid":"001",
  "hotel_id":"92772",
  "check_in_date":"2021-03-28"
}
```
响应：
```
{
  "_index" : "march_log",
  "_type" : "_doc",
  "_id" : "001",
  "_version" : 1,
  "result" : "created",
  "_shards" : {
    "total" : 2,
    "successful" : 1,
    "failed" : 0
  },
  "_seq_no" : 0,
  "_primary_term" : 1
}

```
创建索引别名
``` 
POST /_aliases
{
  "actions": [
    {
      "add": {
        "index": "january_log",
        "alias": "last_three_month"
      }
    },
    {
      "add": {
        "index": "february_log",
        "alias": "last_three_month"
      }
    },
    {
      "add": {
        "index": "march_log",
        "alias": "last_three_month"
      }
    }
  ]
}
```
响应：
```
{
  "acknowledged" : true
}
```
从别名进行查询
```
GET /last_three_month/_search
{
  "query": {
    "term": {
      "uid": {
        "value": "001"
      }
    }
  }
}
```
响应：
```
{
  "took" : 3,
  "timed_out" : false,
  "_shards" : {
    "total" : 3,
    "successful" : 3,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : {
      "value" : 3,
      "relation" : "eq"
    },
    "max_score" : 0.2876821,
    "hits" : [
      {
        "_index" : "february_log",
        "_type" : "_doc",
        "_id" : "001",
        "_score" : 0.2876821,
        "_source" : {
          "uid" : "001",
          "hotel_id" : "33224",
          "check_in_date" : "2021-02-23"
        }
      },
      {
        "_index" : "january_log",
        "_type" : "_doc",
        "_id" : "001",
        "_score" : 0.2876821,
        "_source" : {
          "uid" : "001",
          "hotel_id" : "92772",
          "check_in_date" : "2021-01-05"
        }
      },
      {
        "_index" : "march_log",
        "_type" : "_doc",
        "_id" : "001",
        "_score" : 0.2876821,
        "_source" : {
          "uid" : "001",
          "hotel_id" : "92772",
          "check_in_date" : "2021-03-28"
        }
      }
    ]
  }
}
```

向别名索引写入：
```
POST /last_three_month/_doc/002
{
  "uid":"002",
  "hotel_id":"92772",
  "check_in_date":"2021-01-28"
}
```
响应：
```
{
  "error" : {
    "root_cause" : [
      {
        "type" : "illegal_argument_exception",
        "reason" : "no write index is defined for alias [last_three_month]. The write index may be explicitly disabled using is_write_index=false or the alias points to multiple indices without one being designated as a write index"
      }
    ],
    "type" : "illegal_argument_exception",
    "reason" : "no write index is defined for alias [last_three_month]. The write index may be explicitly disabled using is_write_index=false or the alias points to multiple indices without one being designated as a write index"
  },
  "status" : 400
}
```
解决方法，设置某个具体索引可以写入数据
```
POST /_aliases
{
  "actions": [
    {
      "add": {
        "index": "january_log",
        "alias": "last_three_month",
        "is_write_index":true
      }
    }
  ]
}
```
查看索引的映射：
```
GET /january_log/_mapping
```

bulk写入
```
POST /_bulk
{"index":{"_index":"hotel_order","_id":"001"}}
{"order_id":"001","user_id":"user_00x","user_name":"Michael Jordan","hotel_id":"h001"}
{"index":{"_index":"hotel_order","_id":"002"}}
{"order_id":"002","user_id":"user_00a","user_name":"Stephen Jordan","hotel_id":"h0500"}
{"index":{"_index":"hotel_order","_id":"003"}}
{"order_id":"003","user_id":"user_30e","user_name":"Tim Jordan","hotel_id":"h0520"}
{"index":{"_index":"hotel_order","_id":"004"}}
{"order_id":"004","user_id":"user_430","user_name":"Kobe Jordan","hotel_id":"h0600"}


PUT /hotel_order
{
  "mappings": {
    "properties": {
      "order_id":{
        "type": "keyword"
      },
      "user_id":{
        "type": "keyword"
      },
      "user_name":{
        "type": "text",
        "fields": {
          "user_name_keyword":{
            "type":"keyword"
          }
        }
      },
      "hotel_id":{
        "type": "keyword"
      }
    }
  }
}
```
响应：
```
{
  "took" : 145,
  "errors" : false,
  "items" : [
    {
      "index" : {
        "_index" : "hotel_order",
        "_type" : "_doc",
        "_id" : "001",
        "_version" : 1,
        "result" : "created",
        "_shards" : {
          "total" : 2,
          "successful" : 1,
          "failed" : 0
        },
        "_seq_no" : 0,
        "_primary_term" : 1,
        "status" : 201
      }
    },
    {
      "index" : {
        "_index" : "hotel_order",
        "_type" : "_doc",
        "_id" : "002",
        "_version" : 1,
        "result" : "created",
        "_shards" : {
          "total" : 2,
          "successful" : 1,
          "failed" : 0
        },
        "_seq_no" : 1,
        "_primary_term" : 1,
        "status" : 201
      }
    },
    {
      "index" : {
        "_index" : "hotel_order",
        "_type" : "_doc",
        "_id" : "003",
        "_version" : 1,
        "result" : "created",
        "_shards" : {
          "total" : 2,
          "successful" : 1,
          "failed" : 0
        },
        "_seq_no" : 2,
        "_primary_term" : 1,
        "status" : 201
      }
    },
    {
      "index" : {
        "_index" : "hotel_order",
        "_type" : "_doc",
        "_id" : "004",
        "_version" : 1,
        "result" : "created",
        "_shards" : {
          "total" : 2,
          "successful" : 1,
          "failed" : 0
        },
        "_seq_no" : 3,
        "_primary_term" : 1,
        "status" : 201
      }
    }
  ]
}
```

查询(匹配user_name中有Jordan的用户，并按照user_name进行排序):
```
GET /hotel_order/_search
{
  "query": {
    "match": {
      "user_name": "Jordan"
    }
  },
  "sort": [
    {
      "user_name.keyword": {
        "order": "asc"
      }
    }
  ]
}
```

相关性
搜索结果和查询条件的相关程度。文档的_score字段值，命中的文档按照该字段的值进行降序排列
使用function score对文档完成基于规则权重或者衰减函数的打分
使用script score方式，支持用户直接编写脚本代码对文档进行打分

TF-IDF模型
term frequency-inverse document frequency 词频-逆文档频率
是文本挖掘和信息检索工作中数据处理的常用加权技术之一，主要作用是评估词语在文档中的重要程度
组成：
TF：某个词语在文本中的词频，词频越大，TF值越大
IDF：文档的重要程度与包含某个词的文档个数是负相关

BM25算法
使用的是概率模型，属于bag-of-words模型，只考虑文档中单个词的词频，不考虑句子结构或词与词之间的顺序等关系
在默认情况下，这些查询的权重都为1

boost值的设置只限定在term查询和类match查询中
当该值在0～1时表示对权重起负向作用，当该值大于1时表示对权重起正向作用
ES的boosting查询分为两部分，一部分是positive查询，代表正向查询，另一部分是negative查询，代表负向查询。
可以通过negative_boost参数设置负向查询的权重系数，该值的范围为0～1。
最终的文档得分为：正向匹配值+负向匹配值×negative_boost

Function Score支持的函数及作用
script_score：用户自定义脚本函数。script_score子句中的结果必须大于或者等于0，不能为负数，否则，ES将会报错，
还可以使用params为script_score传递参数
weight：权重，weight函数提供的是一个系数，最终的得分等于对原有评分乘以这个系数
random_score：随机函数，产生0～1的随机小数，但是不包括1。在默认情况下，该随机函数使用的随机种子为文档_id，
可以通过seed参数指定随机数种子
field_value_factor：字段值因子
衰减函数：gauss、linear、exp等

空值处理
value_count聚合

单维度桶聚合
按照一个维度对文档进行分组聚合，terms、filter、ranges
terms聚合是按照字段的实际完整值进行匹配和分组的，维度字段必须是keyword、bool、keyword数组等适合精确匹配的数据类型

Top hits聚合指的是聚合时在每个分组内部按照某个规则选出前N个文档进行展示
Collapse聚合，即用户可以在collapse子句中指定分组字段，匹配query的结果按照该字段进行分组，
并在每个分组中按照得分高低展示组内的文档。
当用户在query子句外指定from和size时，将作用在Collapse聚合之后，即此时的分页是作用在分组之后的
