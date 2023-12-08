package design.pattern.structural;
/**
 * One of the structural design pattern
 Adapter design  pattern define a object that works as an adapter so that two unrelated interfaces can work together.
 The object that joins these unrelated interface is called an Adapter.
 The object which client uses is called target
 The object whose behaviour we modify acc to target is known as adaptee
 * */
public class AdapterDesignPattern {
}

/**
    XML->JSON

 * */
class XMLData{   // Adaptee
    String xmlData;
    XMLData(String pXMLData){
        xmlData =  pXMLData;
    }
}

class JsonData{
    String jsonData;
    JsonData(String pJsonData){
        jsonData = pJsonData;
    }
}

class AnalyticsTool{  // Target
    JsonData jsonData;
    AnalyticsTool(JsonData pJsonData){
        jsonData = pJsonData;
    }
    AnalyticsTool(){}

    public void analyzeData(){
        System.out.println("analysing jsonData");
    }
}

class Adapter extends AnalyticsTool {
    XMLData xmlData;   // composition for Adaptee
    Adapter(XMLData pXMLData){
        xmlData = pXMLData;
    }

    @Override
    public void analyzeData() {
        jsonData = xmlToJsonConverter(xmlData); // convert xml to Json
        super.analyzeData();
    }

    private JsonData xmlToJsonConverter(XMLData xmlData){
        return new JsonData(xmlData.xmlData);
    }
}

//class Client{
//    void processData(AnalyticsTool tool){
//        tool.analyzeData();
//    }
//}

class Runner{
    public static void main(String[] args) {
        XMLData xmlData =  new XMLData("xmlData");
//        AnalyticsTool tool  = new AnalyticsTool(xmlData); // don't accept xml data to analyse
        AnalyticsTool tool =  new Adapter(xmlData);
        tool.analyzeData();
//        Client client =  new Client();
//        client.processData(tool);
    }
}