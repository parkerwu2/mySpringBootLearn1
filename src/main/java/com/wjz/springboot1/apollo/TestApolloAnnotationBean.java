package com.wjz.springboot1.apollo;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.springframework.stereotype.Component;

/**
 * Created by jingzhi.wu on 2019/1/10.
 */
@Component
public class TestApolloAnnotationBean {
    @ApolloConfig
    private Config anotherConfig; //inject config for namespace application

    //config change listener for namespace application
    @ApolloConfigChangeListener
    private void anotherOnChange(ConfigChangeEvent changeEvent) {
        System.out.println("Changes for namespace " + changeEvent.getNamespace());
        for (String key : changeEvent.changedKeys()) {
            ConfigChange change = changeEvent.getChange(key);
            System.out.println(String.format("Found change - key: %s, oldValue: %s, newValue: %s, changeType: %s", change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType()));
        }
    }
}
