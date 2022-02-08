field类型批量增加字典：
POST /v1/batch/insert/dictionaries
request：
```json
[{"dictType":"2","dictCode":"array","dictValue":"array","dictDesc":"数组类型"},
{"dictType":"2","dictCode":"boolean","dictValue":"boolean","dictDesc":"布尔类型"},
{"dictType":"2","dictCode":"date","dictValue":"date","dictDesc":"日期类型"},
{"dictType":"2","dictCode":"date_nanos","dictValue":"date_nanos","dictDesc":"日期时间类型"},
{"dictType":"2","dictCode":"keyword","dictValue":"keyword","dictDesc":"关键词类型"},
{"dictType":"2","dictCode":"nested","dictValue":"nested","dictDesc":"内嵌类型"},
{"dictType":"2","dictCode":"long","dictValue":"long","dictDesc":"长整型"},
{"dictType":"2","dictCode":"short","dictValue":"short","dictDesc":"短整型"},
{"dictType":"2","dictCode":"byte","dictValue":"byte","dictDesc":"字节类型"},
{"dictType":"2","dictCode":"double","dictValue":"double","dictDesc":"双精度类型"},
{"dictType":"2","dictCode":"float","dictValue":"float","dictDesc":"浮点型"},
{"dictType":"2","dictCode":"object","dictValue":"object","dictDesc":"对象类型"},
{"dictType":"2","dictCode":"text","dictValue":"text","dictDesc":"文本类型"}
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
分词器字典增加：
POST /v1/batch/insert/dictionaries
request：
```json
[{"dictType":"1","dictCode":"standard","dictValue":"standard","dictDesc":"标准分词器"},
{"dictType":"1","dictCode":"simple","dictValue":"simple","dictDesc":"简单分词器"},
{"dictType":"1","dictCode":"whitespace","dictValue":"whitespace","dictDesc":"空格分词器"},
{"dictType":"1","dictCode":"stop","dictValue":"stop","dictDesc":"停用词分词器"},
{"dictType":"1","dictCode":"keyword","dictValue":"keyword","dictDesc":"关键词分词器"},
{"dictType":"1","dictCode":"pattern","dictValue":"pattern","dictDesc":"模式分词器"}
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
通用字典是否增加：
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
    "code": "00000000",
    "msg": "response success",
    "data": true
}
```