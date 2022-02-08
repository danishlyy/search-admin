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