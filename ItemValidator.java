package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Arrays;

@FacesValidator
public class ItemValidator implements Validator {
    private String[] itemTypes = {"Cake", "Cupcake", "Cookie", "Brownie", "Muffin", "Pie"};


    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object type) {
        if (!Arrays.asList(itemTypes).contains(type)) {
            FacesMessage msg =
                    new FacesMessage("Dessert should be one of the following: " + Arrays.toString(itemTypes));
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}