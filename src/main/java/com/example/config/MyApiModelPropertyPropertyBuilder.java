package com.example.config;

import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.Extension;
import io.swagger.annotations.ExtensionProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.schema.Annotations;
import springfox.documentation.service.ObjectVendorExtension;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;
import springfox.documentation.spring.web.DescriptionResolver;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjiameng
 * @date 2020-08-20 15:37
 */
//@Component
//@Order(2)
public class MyApiModelPropertyPropertyBuilder implements ModelPropertyBuilderPlugin {
    private final DescriptionResolver descriptions;

    @Autowired
    public MyApiModelPropertyPropertyBuilder(DescriptionResolver descriptions) {
        this.descriptions = descriptions;
    }

    @Override
    public void apply(ModelPropertyContext context) {
        Optional<ApiModelProperty> annotation = Optional.absent();

        if (context.getBeanPropertyDefinition().isPresent()) {
            BeanPropertyDefinition beanPropertyDefinition = context.getBeanPropertyDefinition().get();
//            annotation = annotation.or(Annotations.findPropertyAnnotation(beanPropertyDefinition, ApiModelProperty.class));
        }

        if (annotation.isPresent()) {
            context.getBuilder().extensions(annotation.transform(toExtension()).orNull());
        }

    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return SwaggerPluginSupport.pluginDoesApply(delimiter);
    }


    static Function<ApiModelProperty, List<VendorExtension>> toExtension() {
        return new Function<ApiModelProperty, List<VendorExtension>>() {
            @Override
            public List<VendorExtension> apply(ApiModelProperty annotation) {
                Extension[] extensions = annotation.extensions();
                List<VendorExtension> list = new ArrayList<>();
                if (extensions != null && extensions.length > 0) {
                    for (int i = 0; i < extensions.length; i++) {

                        Extension extension = extensions[i];
                        String name = extension.name();
                        ObjectVendorExtension objectVendorExtension = new ObjectVendorExtension(name);
                        if (!name.equals("")) {
                            for (int j = 0; j < extension.properties().length; j++) {
                                ExtensionProperty extensionProperty = extension.properties()[j];
                                StringVendorExtension stringVendorExtension = new StringVendorExtension(extensionProperty.name(), extensionProperty.value());
                                objectVendorExtension.addProperty(stringVendorExtension);
                            }
                            list.add(objectVendorExtension);
                        }
                    }
                }
                return list;
            }
        };
    }

}
