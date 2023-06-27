//package comm.core.api;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
//import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
//import org.springframework.validation.Validator;
//
//public class RestConfig implements RepositoryRestConfigurer {
//
//    @Autowired
//    private final Validator validator;
//
//    public RestConfig(Validator validator) {
//        this.validator = validator;
//    }
//
//    @Override
//    public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
//        validatingListener.addValidator("beforeCreate", validator);
//        validatingListener.addValidator("beforeSave", validator);
//    }
//}
