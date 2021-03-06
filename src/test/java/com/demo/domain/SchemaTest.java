package com.demo.domain;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import javax.persistence.Entity;
import java.util.EnumSet;
import java.util.Set;

/**
 * @author Marc Schneider
 */
public class SchemaTest {
  @Test
  public void generateSchema() {
    StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().applySetting("hibernate.dialect",
                                                                                                        "org.hibernate.dialect.MariaDBDialect")
      .build();

    ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
    provider.addIncludeFilter(new AnnotationTypeFilter(Entity.class));
    Set<BeanDefinition> entities = provider.findCandidateComponents("com.demo.domain");
    MetadataSources metadataSources = new MetadataSources(standardServiceRegistry);
    for (BeanDefinition entity : entities) {
      metadataSources.addAnnotatedClassName(entity.getBeanClassName());
    }

    Metadata metadata = metadataSources.getMetadataBuilder()
      .applyImplicitNamingStrategy(new SpringImplicitNamingStrategy())
      .applyPhysicalNamingStrategy(new SpringPhysicalNamingStrategy())
      .build();

    new SchemaExport()
      //.setOutputFile("target/ddl.sql")
      .setDelimiter(";")
      .setFormat(true)
      .create(EnumSet.of(TargetType.STDOUT), metadata);
  }
}
