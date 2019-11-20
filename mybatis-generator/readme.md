### 项目打包命令
```
mvn -X clean install
```

###  分页插件
```
XxxExample example = new XxxExample();
...
example.setLimit(10); // page size limit
example.setOffset(20); // offset
List<Xxx> list = xxxMapper.selectByExample(example);
```
sql 显示:
`select ... limit 20, 10`

```
XxxExample example = new XxxExample();
...
example.setLimit(10); // limit
List<Xxx> list = xxxMapper.selectByExample(example);
```
sql 显示:
`select ... limit 10`