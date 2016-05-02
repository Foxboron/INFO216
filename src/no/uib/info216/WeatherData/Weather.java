package no.uib.info216.WeatherData;


import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.apache.jena.rdf.model.ModelFactory.createOntologyModel;
import static org.apache.jena.shared.RandomOrderGraph.createDefaultModel;


/**
 * Created by svimanet on 01/03/16.
 */
public class Weather {

    Yr yrno = new Yr();

    private ArrayList<String> dateFrom = yrno.getFromtag();
    private ArrayList<String> nameTag = yrno.getNametag();
    private ArrayList<String> windTag = yrno.getWindSpeedName();
    private ArrayList<Integer> periodTag = yrno.getPeriodTag();
    private ArrayList<String> tempTag = yrno.getTemprature();

    Model model = createDefaultModel();

    int arrayLength = nameTag.size();

    public void createOntology(){

        // Temp
        Property weatherProperty = model.createProperty("http://purl.oclc.org/NET/ssnx/cf/cf-feature");
        Property weatherPropertyTemp = model.createProperty("http://purl.oclc.org/NET/ssnx/qu/dim#Temperature");

        for(int i = 0; i < arrayLength; i++) {

            if (periodTag.contains(2)) {
                String itemdateFrom = this.dateFrom.get(i);
                String itemnameTag = this.nameTag.get(i);
                String itemTempTag = this.tempTag.get(i);

                Resource weatherData
                        = model.createResource(itemdateFrom)
                        .addProperty(weatherProperty, itemnameTag)
                        .addProperty(weatherPropertyTemp, itemTempTag);
            }
        }
    }

    public void writerMethod(){
        OntModel m = createOntologyModel(OntModelSpec.OWL_MEM, model);

        FileWriter out = null;
        try {
            out = new FileWriter( "weather.ttl" );
            m.write( out, "Turtle" );
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {out.close();} catch (IOException ignore) {}
            }
        }
    }

    public Model parse(){
        createOntology();
        return model;
    }

    public static void main(String[] args) {
        Weather weather = new Weather();
        weather.createOntology();
        weather.writerMethod();
    }
}
