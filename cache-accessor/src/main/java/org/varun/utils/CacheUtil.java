package org.varun.utils;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.Configuration;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.client.hotrod.exceptions.HotRodClientException;
import org.infinispan.commons.util.FileLookupFactory;
import org.infinispan.commons.util.Util;
import org.infinispan.protostream.SerializationContextInitializer;
import org.jboss.logging.Logger;
import org.varun.Constants;
import org.varun.entity.QuestionQuestion;
import org.varun.entity.QuestionQuestionInitializerImpl;
import org.varun.entity.QuestionQuestionKey;
import org.varun.entity.QuestionQuestionKeyInitializerImpl;

import javax.enterprise.context.ApplicationScoped;
import java.io.InputStream;
import java.util.Properties;

@ApplicationScoped
public class CacheUtil {

    private final Logger log = Logger.getLogger(CacheUtil.class);

    private RemoteCacheManager remoteCacheManager;

    public CacheUtil() {
        log.info("Initializing RemoteCacheManager");
        initRemoteCacheManager();
        log.info("Initializing RemoteCacheManager complete");
    }

    private void initRemoteCacheManager() {
        ConfigurationBuilder builder = new ConfigurationBuilder();
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        builder.classLoader(cl);

        // load infinispan properties
        InputStream stream = FileLookupFactory.newInstance().lookupFile(Constants.HOTROD_CLIENT_PROPERTIES, cl);

        if (stream == null) {
            log.error("Could not find infinispan hotrod client properties file: " + Constants.HOTROD_CLIENT_PROPERTIES);
            return;
        }

        try {
            builder.withProperties(loadFromStream(stream));
        } finally {
            Util.close(stream);
        }

        // create cache manager
        SerializationContextInitializer questionQuestionSCI = new QuestionQuestionInitializerImpl();
        builder.addContextInitializer(questionQuestionSCI);
        SerializationContextInitializer questionQuestionKeySCI = new QuestionQuestionKeyInitializerImpl();
        builder.addContextInitializer(questionQuestionKeySCI);
        Configuration config = builder.build();
        remoteCacheManager = new RemoteCacheManager(config);
    }

    private Properties loadFromStream(InputStream stream) {
        Properties properties = new Properties();
        try {
            properties.load(stream);
        } catch (Exception e) {
            throw new HotRodClientException("Issues configuring from client hotrod-client.properties", e);
        }
        return properties;
    }

    public QuestionQuestion getQuestionQuestion(QuestionQuestionKey key) {
        if (remoteCacheManager == null) {
            initRemoteCacheManager();
        }
        RemoteCache<QuestionQuestionKey, QuestionQuestion> remoteCache = remoteCacheManager.getCache(Constants.QUESTION_QUESTION_CACHE_NAME);
        return remoteCache.get(key);
    }

    public QuestionQuestionKey saveQuestionQuestion(QuestionQuestion value) {
        if (remoteCacheManager == null) {
            initRemoteCacheManager();
        }
        RemoteCache<QuestionQuestionKey, QuestionQuestion> remoteCache = remoteCacheManager.getCache(Constants.QUESTION_QUESTION_CACHE_NAME);
        QuestionQuestionKey key = new QuestionQuestionKey(value.getRealm(), value.getSourceCode(), value.getTargetCode());
        remoteCache.put(key, value);
        return key;
    }
}
