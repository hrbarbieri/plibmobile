package com.brightstar.plibmobi.ws;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brightstar.mpx.client.BackendBuybackServiceClient;
import com.brightstar.mpx.client.BuybackMpxException;
import com.brightstar.mpx.client.PricingMatrixQuoteOutput;
import com.brightstar.mpx.client.QuoteInput;
import com.brightstar.plibmobi.model.ChecklistType;
import com.brightstar.plibmobi.model.InterfaceConfig;
import com.brightstar.plibmobi.model.dto.ServiceOrderDto;
import com.brightstar.plibmobi.service.UtilsService;
import com.brightstar.plibmobi.util.StringUtil;

@Component
public class BackendBuybackMpxServiceFacade {

    public static final String BEAN = "backendBuybackMPXServiceFacade";
    private final Log logger = LogFactory.getLog(getClass());
    
        
    @Autowired
    private UtilsService utilsService;
    
    
    public PricingMatrixQuoteOutput confirmQuoteDevice(ServiceOrderDto serviceOrder, List<ChecklistType> backs) throws BuybackMpxException {
    	
    	
    	com.brightstar.mpx.client.Question[] questionsExIds = this.getQuestions(backs);
        
        QuoteInput quoteInput = new QuoteInput();
        quoteInput.setHandsetQuoteId(serviceOrder.getHandsetQuoteId());
        quoteInput.setIMEI(serviceOrder.getSerialNumber());
        quoteInput.setPhoneModelId(serviceOrder.getMpxId());
        quoteInput.setNetworkId(serviceOrder.getOperatorXref());
        quoteInput.setQuestions(questionsExIds);


        PricingMatrixQuoteOutput quoteOutput = null;

        try {
            quoteOutput = confirmQuote(quoteInput, serviceOrder.getCountryId());
            return quoteOutput;
        } catch (BuybackMpxException e) {
            logger.debug("Sem acesso aos sistemas BB3/MPX, " + e.getMessage());
            throw new BuybackMpxException("Sem acesso aos sistemas BB3/MPX, " + e.getMessage());
        }

    }
    
    
    private PricingMatrixQuoteOutput confirmQuote(QuoteInput quoteInput, Integer countryId) throws BuybackMpxException {
    	
        BackendBuybackServiceClient client = buildClient(countryId);
        PricingMatrixQuoteOutput quoteOutput = client.confirmQuote(quoteInput);

        return quoteOutput;
    }
    
    private BackendBuybackServiceClient buildClient(Integer countryId) throws BuybackMpxException {

    	
    	List<InterfaceConfig> configs = utilsService.findInterfaceConfigByGroup("BUYBACK_MPX_CLIENT_" + countryId);

        String endPoint = null;
        String username = null;
        String password = null;
    	
        for (InterfaceConfig config : configs) {
        	if (config.getName().equalsIgnoreCase("ENDPOINT")) {
    			endPoint = config.getValue();
    		} else if (config.getName().equalsIgnoreCase("PASSWORD")) {
                password = config.getValue();
            } else if (config.getName().equalsIgnoreCase("USERNAME")) {
                username = config.getValue();
            }
        }
        
        validateRequiredConfiguration(endPoint, "endPoint");
        validateRequiredConfiguration(password, "password");
        validateRequiredConfiguration(username, "username");

        BackendBuybackServiceClient client = new BackendBuybackServiceClient(endPoint, username, password);

        return client;
    }
    
    private void validateRequiredConfiguration(String s, String fieldName) throws BuybackMpxException {
        if (StringUtil.isBlank(s)) {
            throw new BuybackMpxException("Interface configuration '" + fieldName + "' not found on database.");
        }
    }
    
    
    public com.brightstar.mpx.client.Question[] getQuestions(List<ChecklistType> types) {
    	
        List<com.brightstar.mpx.client.Question> questions = new ArrayList<com.brightstar.mpx.client.Question>();

        types.forEach(type ->{
        	com.brightstar.mpx.client.Question question = new com.brightstar.mpx.client.Question();
        	question.setQuestionId(new Long(type.getXref()));
        	question.setResultOK(type.getIsOk().equals("1"));
            
        	questions.add(question);
        });

        return questions.toArray(new com.brightstar.mpx.client.Question[questions.size()]);
    }

}
