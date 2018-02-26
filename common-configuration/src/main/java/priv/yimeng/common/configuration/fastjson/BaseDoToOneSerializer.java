package priv.yimeng.common.configuration.fastjson;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import org.springframework.util.Assert;
import priv.yimeng.common.core.annotations.JsonSerializeField;
import priv.yimeng.common.core.utils.BaseDoSerializeUtils;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Description: 指定需要的字段序列化
 *
 * @author yimeng
 * @version 1.0
 * @see BaseDoSerializeUtils
 * CreateDate:  2018-02-07
 */
public class BaseDoToOneSerializer implements ObjectSerializer {

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        Assert.notNull(object, "object must not be null");
        Assert.notNull(fieldName, "fieldName must not be null");

        JsonSerializeField annotation = BaseDoSerializeUtils.getJsonSerializedFieldOnClass(object.getClass());
        if (annotation == null) {
            serializer.write(object);
        } else {
            BaseDoSerializeUtils.serializeUsingPropertyPreFilter(serializer, object, annotation.value());
        }
    }
}
