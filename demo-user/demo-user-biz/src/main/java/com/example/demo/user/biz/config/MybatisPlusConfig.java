package com.example.demo.user.biz.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;

@Configuration
@EnableTransactionManagement
public class MybatisPlusConfig {

  private static final Log log = LogFactory.getLog(MybatisPlusConfig.class);

  //    mybatisPlus全局配置
  @Bean(name = "globalConfig")
  public GlobalConfiguration globalConfig(
          @Value("${mybatisPlus.globalConfig.idType}") Integer idType, //主键类型  0:"数据库ID自增", 1:"用户输入ID",2:"全局唯一ID (数字类型唯一ID)", 3:"全局唯一ID UUID";
          @Value("${mybatisPlus.globalConfig.fieldStrategy}") Integer fieldStrategy, //字段策略 0:"忽略判断",1:"非 NULL 判断"),2:"非空判断"
          @Value("${mybatisPlus.globalConfig.dbColumnUnderline}") Boolean dbColumnUnderline, //驼峰下划线转换
          @Value("${mybatisPlus.globalConfig.isRefresh}") Boolean isRefresh, //刷新mapper 调试神器
          @Value("${mybatisPlus.globalConfig.isCapitalMode}") Boolean isCapitalMode, //数据库大写下划线转换
          @Value("${mybatisPlus.globalConfig.logicDeleteValue}") String logicDeleteValue, //逻辑删除配置
          @Value("${mybatisPlus.globalConfig.logicNotDeleteValue}") String logicNotDeleteValue //逻辑删除配置
  ) {
    log.info("初始化GlobalConfiguration");
    GlobalConfiguration globalConfig = new GlobalConfiguration();
    if ( !StringUtils.isEmpty(idType)) {
      globalConfig.setIdType(idType);  //主键类型
    }
    if ( !StringUtils.isEmpty(fieldStrategy)) {
      //        globalConfig.setFieldStrategy(fieldStrategy); //字段策略
    }
    if ( !StringUtils.isEmpty(dbColumnUnderline)) {
      globalConfig.setDbColumnUnderline(dbColumnUnderline);  //驼峰下划线转换
    }
    if ( !StringUtils.isEmpty(isRefresh)) {
      //        globalConfig.setRefresh(isRefresh); //刷新mapper 调试神器
    }
    if ( !StringUtils.isEmpty(isCapitalMode)) {
      globalConfig.setCapitalMode(isCapitalMode); //数据库大写下划线转换
    }
    if ( !StringUtils.isEmpty(logicDeleteValue)) {
      //        globalConfig.setLogicDeleteValue(logicDeleteValue);  //逻辑删除配置
    }
    if ( !StringUtils.isEmpty(logicNotDeleteValue)) {
      //        globalConfig.setLogicNotDeleteValue(logicNotDeleteValue);  //逻辑删除配置
    }
    return globalConfig;
  }


  @Bean(name = "sqlSessionFactory")
  public SqlSessionFactory sqlSessionFactory(@Qualifier(value = "globalConfig")GlobalConfiguration globalConfig,
                                             @Qualifier(value = "dataSource")DruidDataSource dataSource) throws Exception {
    log.info("初始化SqlSessionFactory");
    //String mapperLocations = "com.renjie.dao.**.*.xml";
    String mapperLocations = "classpath:/mapper/*.xml";
    String configLocation = "classpath:/mybatis-sqlconfig.xml";
    //String typeAliasesPackage = "com.renjie.entity.**";
    String typeAliasesPackage = "com.example.demo.user.api.entity.**";
    MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
    sqlSessionFactory.setDataSource(dataSource); //数据源
    sqlSessionFactory.setGlobalConfig(globalConfig); //全局配置
    Interceptor[] interceptor = {new PaginationInterceptor()};
    sqlSessionFactory.setPlugins(interceptor); //分页插件
    ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    try {
      //自动扫描Mapping.xml文件
      sqlSessionFactory.setMapperLocations(resolver.getResources(mapperLocations));
      sqlSessionFactory.setConfigLocation(resolver.getResource(configLocation));
      sqlSessionFactory.setTypeAliasesPackage(typeAliasesPackage);
      return sqlSessionFactory.getObject();
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  //    MyBatis 动态扫描
  @Bean
  public MapperScannerConfigurer mapperScannerConfigurer() {
    log.info("初始化MapperScannerConfigurer");
    MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
    //String basePackage = "com.renjie.dao.**";
    String basePackage = "com.example.demo.user.biz.dao.**";
    mapperScannerConfigurer.setBasePackage(basePackage);
    return mapperScannerConfigurer;
  }
}
