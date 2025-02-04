package edu.alexandra.blackjack.architecture;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaAnnotation;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.properties.CanBeAnnotated;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Annotation;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.belongTo;
import static com.tngtech.archunit.core.domain.properties.CanBeAnnotated.Predicates.annotatedWith;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.onionArchitecture;

@AnalyzeClasses(packages = {
        "edu.alexandra.blackjack"
})
public class ArchitectureTest {

    private static final DescribedPredicate<JavaClass> IS_A_TEST_CLASS =
            JavaClass.Predicates.simpleNameEndingWith("Test");

    @ArchTest
    static final ArchRule onion_architecture_defined_by_annotations = onionArchitecture()
            .domainModels("edu.alexandra.blackjack.domain")
            .domainServices(byAnnotation(Service.class))
            .adapter("persistence", byAnnotation(Repository.class))
            .adapter("rest", byAnnotation(RestController.class))
            .ignoreDependency(IS_A_TEST_CLASS, DescribedPredicate.alwaysTrue());

    @ArchTest
    static final ArchRule domain_services_name_ends_in_ServiceImpl = classes().that()
            .resideInAPackage("edu.alexandra.blackjack.domain.service")
            .and().areAnnotatedWith(Service.class)
            .should().haveSimpleNameEndingWith("ServiceImpl")
            .because("Service implementations should follow the naming convention.");

    private static DescribedPredicate<JavaClass> byAnnotation(Class<? extends Annotation> annotationType){
        DescribedPredicate<CanBeAnnotated> annotatedWith = annotatedWith(annotationType);
        return belongTo(annotatedWith).as(annotatedWith.getDescription());
    }

    private static DescribedPredicate<JavaClass> byAnnotation(DescribedPredicate<? super JavaAnnotation<?>> annotationType){
        DescribedPredicate<CanBeAnnotated> annotatedWith = annotatedWith(annotationType);
        return belongTo(annotatedWith).as(annotatedWith.getDescription());
    }

}
