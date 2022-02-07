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