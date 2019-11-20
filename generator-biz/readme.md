### Mybatis分页插件PageHelper
PageHelper 方法使用了静态的 ThreadLocal 参数，分页参数和线程是绑定的
只要你可以保证在 PageHelper 方法调用后紧跟 MyBatis 查询方法，
这就是安全的。因为 PageHelper 在 finally 代码段中自动清除了 ThreadLocal 存储的对象。
```
@Service
public class DocServiceImpl implements IDocService {
    @Autowired
    private DocMapper docMapper;

    @Override
    public PageInfo<Doc> selectDocByPage1(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<Doc> docs = docMapper.selectByPageAndSelections();
        PageInfo<Doc> pageInfo = new PageInfo<>(docs);
        return pageInfo;
    }
}
```
#### 这种写法就能保证安全
```
List<Country> list;
if(param1 != null){
    PageHelper.startPage(1, 10);
    list = countryMapper.selectIf(param1);
} else {
    list = new ArrayList<Country>();
}
```