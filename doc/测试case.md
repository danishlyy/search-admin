集群状态查询api：
url:GET /v1/get/cluster/health

```json
{
    "code": "00000000",
    "msg": "response success",
    "data": {
        "clusterName": "my-application",
        "clusterStatus": "YELLOW"
    }
}
```

分词api验证：
url：POST
request：
```json
{"analyzer":"standard","text":"The 2 QUICK Brown-Foxes jumped over the lazy dog's bone."}
```
response:
```json
{
    "code":"00000000",
    "msg":"response success",
    "data":"[{\"term\":\"the\",\"startOffset\":0,\"endOffset\":3,\"position\":0,\"positionLength\":1,\"type\":\"<ALPHANUM>\",\"attributes\":{}},{\"term\":\"2\",\"startOffset\":4,\"endOffset\":5,\"position\":1,\"positionLength\":1,\"type\":\"<NUM>\",\"attributes\":{}},{\"term\":\"quick\",\"startOffset\":6,\"endOffset\":11,\"position\":2,\"positionLength\":1,\"type\":\"<ALPHANUM>\",\"attributes\":{}},{\"term\":\"brown\",\"startOffset\":12,\"endOffset\":17,\"position\":3,\"positionLength\":1,\"type\":\"<ALPHANUM>\",\"attributes\":{}},{\"term\":\"foxes\",\"startOffset\":18,\"endOffset\":23,\"position\":4,\"positionLength\":1,\"type\":\"<ALPHANUM>\",\"attributes\":{}},{\"term\":\"jumped\",\"startOffset\":24,\"endOffset\":30,\"position\":5,\"positionLength\":1,\"type\":\"<ALPHANUM>\",\"attributes\":{}},{\"term\":\"over\",\"startOffset\":31,\"endOffset\":35,\"position\":6,\"positionLength\":1,\"type\":\"<ALPHANUM>\",\"attributes\":{}},{\"term\":\"the\",\"startOffset\":36,\"endOffset\":39,\"position\":7,\"positionLength\":1,\"type\":\"<ALPHANUM>\",\"attributes\":{}},{\"term\":\"lazy\",\"startOffset\":40,\"endOffset\":44,\"position\":8,\"positionLength\":1,\"type\":\"<ALPHANUM>\",\"attributes\":{}},{\"term\":\"dog's\",\"startOffset\":45,\"endOffset\":50,\"position\":9,\"positionLength\":1,\"type\":\"<ALPHANUM>\",\"attributes\":{}},{\"term\":\"bone\",\"startOffset\":51,\"endOffset\":55,\"position\":10,\"positionLength\":1,\"type\":\"<ALPHANUM>\",\"attributes\":{}}]"
}
```
批量新增字典:
POST /v1/batch/insert/dictionaries
request:
```json
[{"dictType":"","dictCode":"","dictValue":"","dictDesc":""},
{"dictType":"","dictCode":"","dictValue":"","dictDesc":""},
{"dictType":"","dictCode":"","dictValue":"","dictDesc":""}
]
```
response:
```json
{
    "code": "99999998",
    "msg": "字典代码不可以为空;字典值不可以为空;字典描述不可以为空;字典类型不可为空",
    "data": null
}
```

批量新增字典重复字典code校验case：
POST /v1/batch/insert/dictionaries
request：
```json
[{"dictType":"3","dictCode":"0","dictValue":"0","dictDesc":"是"},
{"dictType":"3","dictCode":"1","dictValue":"1","dictDesc":"否"}
]
```
response：
```json
{
    "code": "10000009",
    "msg": "dictType_dictCode 3_0;3_1  is repeatable ",
    "data": null
}
```

字典查询必填参数验证：
GET /v1/query/dictionary?dictionaryType=
request：
```json

```
response：
```json
{
    "code": "10000008",
    "msg": "dictionary type is null ",
    "data": null
}
```

字典查询case：
GET /v1/query/dictionary?dictionaryType=1
response：
```json
{
  "code": "00000000",
  "msg": "response success",
  "data": [
    {
      "id": "1490881719767732225",
      "dictCode": "standard",
      "dictDesc": "标准分词器"
    },
    {
      "id": "1490881719771926529",
      "dictCode": "simple",
      "dictDesc": "简单分词器"
    },
    {
      "id": "1490881719776120834",
      "dictCode": "whitespace",
      "dictDesc": "空格分词器"
    },
    {
      "id": "1490881719784509441",
      "dictCode": "stop",
      "dictDesc": "停用词分词器"
    },
    {
      "id": "1490881719788703746",
      "dictCode": "keyword",
      "dictDesc": "关键词分词器"
    },
    {
      "id": "1490881719792898049",
      "dictCode": "pattern",
      "dictDesc": "模式分词器"
    }
  ]
}
```

根据字典id批量删除字典id非空验证：
POST /v1/batch/delete/dictionaries
request：
```json
[
    {
        "id":""
    }
]
```
response：
```json
{
    "code": "99999998",
    "msg": "字典id不可为空",
    "data": null
}
```

字典根据id批量删除case验证：
POST /v1/batch/delete/dictionaries
request：
```json
[
    {
        "id":"1490883074817314818"
    },
    {
        "id":"1490883074427244546"
    }
]
```
response：
```json
{
    "code": "00000000",
    "msg": "response success",
    "data": true
}
```

字典批量更新必填校验case：
POST /v1/batch/update/dictionaries
request：
```json
[
    {"id":"", "dictType":"3","dictCode":"0","dictValue":"0","dictDesc":"是test"},
    {"id":"","dictType":"3","dictCode":"1","dictValue":"1","dictDesc":"否test"}
]
```
response：
```json
{
    "code": "99999998",
    "msg": "字典id不可为空",
    "data": null
}
```

字典批量更新字典描述信息：
POST /v1/batch/update/dictionaries
request：
```json
[
    {"id":"1490883074427244546", "dictType":"3","dictCode":"0","dictValue":"0","dictDesc":"是test"},
    {"id":"1490883074817314818","dictType":"3","dictCode":"1","dictValue":"1","dictDesc":"否test"}
]
```
response：
```json
{
  "code": "00000000",
  "msg": "response success",
  "data": true
}
```

新增索引名称、分片、副本数参数校验不通过case：
POST /v1/create/index/setting
request：
```json
{"indexName":"","numberOfShards":"","numberOfReplicas":""}
```
response：
```json
{
    "code": "99999998",
    "msg": "indexName is not correct,please refer to https://www.elastic.co/guide/en/elasticsearch/reference/7.0/indices-create-index.html;numberOfReplicas cannot be null,at least 1;numberOfShards cannot be null,at least 1;不能为空",
    "data": null
}
```
新增索引名称、分片、副本成功case：
POST /v1/create/index/setting
request：
```json
{"indexName":"product_info","indexDesc":"产品信息","numberOfShards":"1","numberOfReplicas":"2"}
```
response：
```json
{
    "code": "00000000",
    "msg": "response success",
    "data": true
}
```
重复新增索引名称、分片、副本失败case：
POST /v1/create/index/setting
request：
```json
{"indexName":"product_info","indexDesc":"产品信息","numberOfShards":"1","numberOfReplicas":"2"}
```
response：
```json
{
    "code": "10000001",
    "msg": "indexName product_info has exist",
    "data": null
}
```
修改索引副本、描述信息不传id校验失败case：
POST /v1/update/index/setting
request：
```json
{"indexId":"","indexDesc":"产品信息test","numberOfReplicas":"3"}
```
response：
```json
{
    "code": "99999998",
    "msg": "索引id不可以为空",
    "data": null
}
```
修改索引副本、描述信息成功case：
POST /v1/update/index/setting
request：
```json
{"indexId":"1491330950936715265","indexDesc":"产品信息test","numberOfReplicas":"3"}
```
response：
```json
{
    "code": "00000000",
    "msg": "response success",
    "data": true
}
```

根据索引id查询索引名称、描述、分片、副本成功case：
GET /v1/get/index/setting?indexId=1491330950936715265
request：
```json

```
response：
```json
{
    "code": "00000000",
    "msg": "response success",
    "data": {
        "indexId": "1491330950936715265",
        "indexName": "product_info",
        "numberOfShards": "1",
        "numberOfReplicas": "3"
    }
}
```

根据索引id查询索引名称、描述、分片、副本失败case：
GET /v1/get/index/setting?indexId=1491330950936715265
request：
```json
{
    "code": "10000003",
    "msg": "index not exist",
    "data": null
}
```
response：
```json
{
  "code": "00000000",
  "msg": "response success",
  "data": {
    "indexId": "1491330950936715265",
    "indexName": "product_info",
    "indexDesc": "产品信息test",
    "numberOfShards": "1",
    "numberOfReplicas": "3"
  }
}
```

根据索引id批量删除索引信息必填校验case：
POST /v1/delete/indexes
request
```json
[
  {
    "indexId":""
  },
  {
    "indexId":""
  }
]
```
response：
```json
{
    "code": "99999998",
    "msg": "索引id不可以为空",
    "data": null
}
```

根据索引id批量删除索引信息必填校验case：
POST /v1/delete/indexes
request
```json
[
  {
    "indexId":"1491330950936715265"
  },
  {
    "indexId":"1491349710993129474"
  }
]
```
response：
```json
{
  "code": "00000000",
  "msg": "response success",
  "data": true
}
```

基于索引进行文本分词验证成功case：
POST /v1/custom/analyze/text
request：
```json
{
  "analyzer": "whitespace",
  "text": "Michael Jordan",
  "indexName":"hotel_order"
}
```
response：
```json
{
    "code": "00000000",
    "msg": "response success",
    "data": "[{\"term\":\"michael\",\"startOffset\":0,\"endOffset\":7,\"position\":0,\"positionLength\":1,\"type\":\"<ALPHANUM>\",\"attributes\":{}},{\"term\":\"jordan\",\"startOffset\":8,\"endOffset\":14,\"position\":1,\"positionLength\":1,\"type\":\"<ALPHANUM>\",\"attributes\":{}}]"
}
```

基于索引进行文本分词验证失败case：
POST /v1/custom/analyze/text
request：
```json
{
  "analyzer": "",
  "text": "",
  "indexName":""
}
```
response：
```json
{
  "code": "99999998",
  "msg": "分词器不可以为空;索引名称必须有;需要分词的文本不可以为空",
  "data": null
}
```

ES索引列表 分页查询参数校验失败case：
GET /v1/page/query/indexes
request：
```json
{"pageNumber":"","pageSize":"","indexName":"","indexStatus":""}
```
response：
```json
{
    "code": "99999998",
    "msg": "分页参数必传;每页查询的条数必传",
    "data": null
}
```
ES索引列表 分页查询成功case
GET /v1/page/query/indexes
request：
```json
{"pageNumber":"1","pageSize":"10","indexName":"","indexStatus":""}
```
response：
```json
{
  "code": "00000000",
  "msg": "response success",
  "data": {
    "pages": "1",
    "total": "2",
    "current": "1",
    "records": [
      {
        "indexId": "1491349710993129474",
        "indexName": "trade_info",
        "indexDesc": "交易信息",
        "indexStatusDesc": "有效",
        "indexStatus": "0",
        "numberOfShards": "1",
        "numberOfReplicas": "1"
      },
      {
        "indexId": "1491330950936715265",
        "indexName": "product_info",
        "indexDesc": "产品信息test",
        "indexStatusDesc": "有效",
        "indexStatus": "0",
        "numberOfShards": "1",
        "numberOfReplicas": "3"
      }
    ]
  }
}
```

request：
```json
{"pageNumber":"1","pageSize":"10","indexName":"product_info","indexStatus":""}
```
response：
```json
{
    "code": "00000000",
    "msg": "response success",
    "data": {
        "pages": "1",
        "total": "1",
        "current": "1",
        "records": [
            {
                "indexId": "1491330950936715265",
                "indexName": "product_info",
                "indexDesc": "产品信息test",
                "indexStatusDesc": "有效",
                "indexStatus": "0",
                "numberOfShards": "1",
                "numberOfReplicas": "3"
            }
        ]
    }
}
```
request：
```json
{"pageNumber":"1","pageSize":"10","indexName":"product_info","indexStatus":"1"}
```
response：
```json
{
    "code": "00000000",
    "msg": "response success",
    "data": {
        "pages": "0",
        "total": "0",
        "current": "1",
        "records": []
    }
}
```
request：
```json
{"pageNumber":"2","pageSize":"10","indexName":"","indexStatus":""}
```
response：
```json
{
    "code": "00000000",
    "msg": "response success",
    "data": {
        "pages": "1",
        "total": "2",
        "current": "2",
        "records": []
    }
}
```
新增索引映射关系成功case
POST /v1/create/index/mapping
request：
```json
{"indexId":"1491330950936715265","indexName":"product_info","fields":[{"fieldName":"productCode","fieldType":"keyword","analyzeFlag":"1","analyzeType":""},
{"fieldName":"productName","fieldType":"text","analyzeFlag":"0","analyzeType":"standard"}]}
```
response：
```json
{
    "code": "00000000",
    "msg": "response success",
    "data": true
}
```
新增索引时必填校验case
POST /v1/create/index/mapping
request：
```json
{"indexId":"1491330950936715265","indexName":"product_info","fields":[{"fieldName":"","fieldType":"keyword","analyzeFlag":"1","analyzeType":""},
  {"fieldName":"productName","fieldType":"text","analyzeFlag":"0","analyzeType":"standard"}]}
```
response：
```json
{
  "code": "99999998",
  "msg": "索引fieldName不可以为空",
  "data": null
}
```

更新索引映射信息成功case[字段名不可修改，字段类型，是否分词、分词类型可修改]：
POST /v1/update/index/mapping
request：
```json
{"indexId":"1491330950936715265","indexName":"product_info","fields":[{"fieldName":"productCode","fieldType":"keyword","analyzeFlag":"1","analyzeType":""},
{"fieldName":"productName","fieldType":"text","analyzeFlag":"0","analyzeType":"standard"},{"fieldName":"productType","fieldType":"keyword","analyzeFlag":"1","analyzeType":""}]}
```
response：
```json
{
    "code": "00000000",
    "msg": "response success",
    "data": true
}
```

更新索引映射信息必填校验失败case：
POST /v1/update/index/mapping
request：
```json
{"indexId":"1491330950936715265","indexName":"product_info","fields":[{"fieldName":"productCode","fieldType":"keyword","analyzeFlag":"1","analyzeType":""},
{"fieldName":"","fieldType":"text","analyzeFlag":"0","analyzeType":"standard"},{"fieldName":"productType","fieldType":"","analyzeFlag":"1","analyzeType":""}]}
```
response：
```json
{
    "code": "99999998",
    "msg": "field的类型不合法;索引fieldName不可以为空;索引fieldType不可以为空",
    "data": null
}
```

根据索引id查询索引映射信息校验失败case：
GET /v1/get/index/mapping
request：
```json
{
    "indexId":""
}
```
response：
```json
{
    "code": "99999998",
    "msg": "索引id不可以为空",
    "data": null
}
```

根据索引id查询索引映射信息成功case：
GET /v1/get/index/mapping
request：
```json
{
    "indexId":"1491330950936715265"
}
```
response：
```json
{
  "code": "00000000",
  "msg": "response success",
  "data": {
    "indexId": "1491330950936715265",
    "indexName": "product_info",
    "fields": [
      {
        "fieldName": "productCode",
        "fieldType": "keyword",
        "analyzeFlag": "1",
        "analyzeType": ""
      },
      {
        "fieldName": "productName",
        "fieldType": "text",
        "analyzeFlag": "0",
        "analyzeType": "standard"
      },
      {
        "fieldName": "productType",
        "fieldType": "keyword",
        "analyzeFlag": "1",
        "analyzeType": ""
      }
    ]
  }
}
```

通知审核功能入参校验不通过case：
POST /v1/add/notice/info
request：
```json
{"indexId":"","indexName":"","indexStatus":"","syncType":""}
```
response：
```json
{
    "code": "99999998",
    "msg": "同步类型必填;索引id不可以为空;索引名称不可以为空;索引的状态不可以为空",
    "data": null
}
```

通知审核功能成功case：
POST /v1/add/notice/info
request：
```json
{"indexId":"1491330950936715265","indexName":"product_info","indexStatus":"0","syncType":"2"}
```
response：
```json
{
    "code": "00000000",
    "msg": "response success",
    "data": true
}
```

索引删除审核校验case
POST /v1/audit/delete/index
request：
```json
{"indexName":"","id":""}
```
response：
```json
{
    "code": "99999998",
    "msg": "审核记录唯一条件id不可为空;索引名称不可以为空",
    "data": null
}
```

索引删除审核成功case
POST /v1/audit/delete/index
request：
```json
{"indexName":"my_index","id":"1493065344852451329"}
```
response：
```json
{
  "code": "00000000",
  "msg": "response success",
  "data": true
}
```

审核分页查询case成功：
POST /v1/audit/query
request：
```json
{
    "pageNumber":"1",
    "pageSize":"10",
    "indexName": "product_info"
}
```
response：
```json
{
    "code": "00000000",
    "msg": "response success",
    "data": {
        "pages": "1",
        "total": "1",
        "current": "1",
        "records": [
            {
                "id": "1493496606616526850",
                "indexName": "product_info",
                "indexStatus": "有效",
                "auditType": "待审核",
                "syncStatus": "待同步",
                "reindexFlag": "是",
                "reindexStatus": null,
                "modifier": "system",
                "modifyTime": "2022-02-15 16:05:13",
                "noticeTime": "2022-02-15 16:05:13",
                "indexSettingsId": "1491330950936715265",
                "syncType": "全部同步",
                "auditSettingBtnFlag": "0",
                "auditMappingBtnFlag": "0",
                "auditDeleteIndexBtnFlag": "1",
                "auditReIndexBtnFlag": "1"
            }
        ]
    }
}
```