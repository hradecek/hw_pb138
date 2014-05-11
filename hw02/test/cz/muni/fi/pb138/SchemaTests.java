/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pb138;

import cz.muni.fi.pb138.task2.SchemaValidator;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author bar
 */
public class SchemaTests {
    private SchemaValidator validator;
    private static Properties settings;
    
    public SchemaTests() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
        try {
            settings = new Properties();
            settings.load(new FileInputStream("SchemaTest.properties"));
        } catch (IOException ex) {
            System.err.println("Chybi soubor s nastavenimi.");
            System.exit(-1);
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        validator = new SchemaValidator("company.xsd");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void fullCorrectDocument(){
        try{
            String msg=validator.validate("company.xml");
            assertNull(msg,msg);
        }catch(IOException ioe){
            fail("Unable to load company.xml: " + ioe.getMessage());
        }
    }
    
    @Test
    public void nonameCompany(){
        try{
            String msg = validator.validate("nonamecompany.xml");
            assertNull(msg,msg);
        }catch(IOException ioe){
            fail("Unable to load nonamecompany.xml" + ioe.getMessage());
        }
    }
    
    @Test
    public void noProductsCompany(){
        try{
            String msg = validator.validate("noproductscompany.xml");
            assertNull(msg,msg);
        }catch(IOException ioe){
            fail("Unable to load nonamecompany.xml" + ioe.getMessage());
        }
    }
    
    @Test
    public void noDivisionHead(){
        try{
            String msg = validator.validate("noheadcompany.xml");
            assertNull(msg,msg);
        }catch(IOException ioe){
            fail("Unable to load nonamecompany.xml" + ioe.getMessage());
        }
    }
    
    @Test
    public void missingDivision(){
        try {
            System.out.println("Nutno zkontrolovat, ze chyba se tyka pouze chybejiciho elementu division:");
            String msg = validator.validate("nodivisioncompany.xml");
            System.out.println(msg);
            System.out.println();
            assertNotNull("Validace nodivisioncompany.xml nemela projit.",msg);
            
            
        } catch (IOException ex) {
            Logger.getLogger(SchemaTests.class.getName()).log(Level.SEVERE, null, ex);
            fail("Chyba pri validaci nodivisioncompany.xml:\n " +ex.getMessage());
        }
        
    }
    
    @Test 
    public void missingDid(){
        try {
            System.out.println("Nutno zkontrolovat, ze chyba se tyka pouze chybejiciho atributu did:");
            String msg = validator.validate("nodividcompany.xml");
            System.out.println(msg);
            System.out.println();
            assertNotNull("Validace nodividcompany.xml nemela projit.",msg);
         } catch (IOException ex) {
            Logger.getLogger(SchemaTests.class.getName()).log(Level.SEVERE, null, ex);
            fail("Chyba pri validaci nodivisioncompany.xml:\n " +ex.getMessage());
        }
    }
    
    @Test
    public void nonUniqueDid(){
        try {
            System.out.println("Nutno zkontrolovat, ze chyba se tyka pouze chybejiciho atributu did:");
            String msg = validator.validate("duplicitdidcompany.xml");
            System.out.println(msg);
            System.out.println();
            assertNotNull("Validace duplicitdidcompany.xml nemela projit.",msg);
         } catch (IOException ex) {
            Logger.getLogger(SchemaTests.class.getName()).log(Level.SEVERE, null, ex);
            fail("Chyba pri validaci duplicitdidcompany.xml:\n " +ex.getMessage());
        }
    }
    
    @Test
    public void missingChief(){
        try {
            String msg = validator.validate("missingheadcompany.xml");
            assertNull("Neocekavana chyba pri validaci missingheadcompany.xml:",msg);
         } catch (IOException ex) {
            Logger.getLogger(SchemaTests.class.getName()).log(Level.SEVERE, null, ex);
            fail("Chyba pri validaci missingheadcompany.xml:\n " +ex.getMessage());
        }
    } 
    
    @Test 
    public void missingChiefName(){
        try {
            System.out.println("missingheadnamecompany:");
            System.out.println("Nutno zkontrolovat spravnost chyby:");
            String msg = validator.validate("missingheadnamecompany.xml");
            System.out.println(msg);
            System.out.println();
            assertNotNull("Validace missingheadnamecompany.xml nemela projit.",msg);
         } catch (IOException ex) {
            Logger.getLogger(SchemaTests.class.getName()).log(Level.SEVERE, null, ex);
            fail("Chyba pri validaci missingheadnamecompany.xml:\n " +ex.getMessage());
        }
    }
    
    @Test
    public void missingChiefSName(){
        
        try {
            System.out.println("missingheadsnamecompany:");
            System.out.println("Nutno zkontrolovat spravnost chyby:");
            String msg = validator.validate("missingheadsnamecompany.xml");
            System.out.println(msg);
            System.out.println();
            assertNotNull("Validace missingheadsnamecompany.xml nemela projit.",msg);
         } catch (IOException ex) {
            Logger.getLogger(SchemaTests.class.getName()).log(Level.SEVERE, null, ex);
            fail("Chyba pri validaci missingheadsnamecompany.xml:\n " +ex.getMessage());
        }
    } 
    
    @Test
    public void missingChiefPid(){
        try {
            System.out.println("Nutno zkontrolovat, ze chyba se tyka pouze chybejiciho atributu pid u sefa spolecnosti:");
            String msg = validator.validate("missingheadpidcompany.xml");
            System.out.println(msg);
            System.out.println();
            assertNotNull("Validace missingheadpidcompany.xml nemela projit.",msg);
         } catch (IOException ex) {
            Logger.getLogger(SchemaTests.class.getName()).log(Level.SEVERE, null, ex);
            fail("Chyba pri validaci missingheadpidcompany.xml:\n " +ex.getMessage());
        }
    }
    
    @Test
    public void duplicitPid(){
        try {
            System.out.println("duplicitheadpidcompany:");
            System.out.println("Nutno zkontrolovat, spravnost chyby:");
            String msg = validator.validate("duplicitheadpidcompany.xml");
            System.out.println(msg);
            System.out.println();
            assertNotNull("Validace duplicitheadpidcompany.xml nemela projit.",msg);
         } catch (IOException ex) {
            Logger.getLogger(SchemaTests.class.getName()).log(Level.SEVERE, null, ex);
            fail("Chyba pri validaci duplicitheadpidcompany.xml:\n " +ex.getMessage());
        }
    }
    
    
    @Test
    public void missingSalary(){
         try {
            System.out.println("missingheadsalarycompany:");
            System.out.println("Nutno zkontrolovat, spravnost chyby:");
            String msg = validator.validate("missingheadsalarycompany.xml");
            System.out.println(msg);
            System.out.println();
            assertNotNull("Validace missingheadsalarycompany.xml nemela projit.",msg);
         } catch (IOException ex) {
            Logger.getLogger(SchemaTests.class.getName()).log(Level.SEVERE, null, ex);
            fail("Chyba pri validaci missingheadsalarycompany.xml:\n " +ex.getMessage());
        }
    }
    
    @Test
    public void salaryNotNumber(){
        System.out.println("Salary not number:");
        try {
            System.out.println("Nutno zkontrolovat, spravnost chyby:");
            String msg = validator.validate("headsalarynotnumbercompany.xml");
            System.out.println(msg);
            System.out.println();
            assertNotNull("Validace headsalarynotnumbercompany.xml nemela projit.",msg);
         } catch (IOException ex) {
            Logger.getLogger(SchemaTests.class.getName()).log(Level.SEVERE, null, ex);
            fail("Chyba pri validaci headsalarynotnumbercompany.xml:\n " +ex.getMessage());
        }
    }
    
    @Test
    public void salaryFormat(){
        System.out.println("Salary format:");
        try {
            System.out.println("Nutno zkontrolovat, spravnost chyby:");
            String msg = validator.validate("headsalaryformatcompany.xml");
            System.out.println(msg);
            System.out.println();
            assertNotNull("Validace headsalaryformatcompany.xml nemela projit. Plat musi byt na 2 desetinna mista.",msg);
         } catch (IOException ex) {
            Logger.getLogger(SchemaTests.class.getName()).log(Level.SEVERE, null, ex);
            fail("Chyba pri validaci headsalaryformatcompany.xml:\n " +ex.getMessage());
        }
    }
    
    @Test
    public void testNote(){
        System.out.println("Notes:");
        try {
            System.out.println("Nutno zkontrolovat, spravnost chyby:");
            String msg = validator.validate("notetestcompany.xml");
            //System.out.println(msg);
            System.out.println();
            assertNull("notetestcompany.xml mel byt v poradku.",msg);
         } catch (IOException ex) {
            Logger.getLogger(SchemaTests.class.getName()).log(Level.SEVERE, null, ex);
            fail("Chyba pri validaci notetestcompany.xml:\n " +ex.getMessage());
        }
    }
    
        
    @Test
    public void mandatoryProduct(){
        System.out.println("Chybejici Product:");
        try {
            System.out.println("Nutno zkontrolovat, spravnost chyby:");
            String msg = validator.validate("missingproductcompany.xml");
            System.out.println(msg);
            System.out.println();
            assertNotNull("missingproductcompany.xml obsahuje chybu.",msg);
         } catch (IOException ex) {
            Logger.getLogger(SchemaTests.class.getName()).log(Level.SEVERE, null, ex);
            fail("Chyba pri validaci missingproductcompany.xml:\n " +ex.getMessage());
        }
    }
    
    @Test
    public void mandatoryProductCategory(){
        System.out.println("Chybejici Category u Product:");
        try {
            System.out.println("Nutno zkontrolovat, spravnost chyby:");
            String msg = validator.validate("missingproductcategorycompany.xml");
            System.out.println(msg);
            System.out.println();
            assertNotNull("missingproductcategorycompany.xml obsahuje chybu.",msg);
         } catch (IOException ex) {
            Logger.getLogger(SchemaTests.class.getName()).log(Level.SEVERE, null, ex);
            fail("Chyba pri validaci missingproductcategorycompany.xml:\n " +ex.getMessage());
        }
    }
    
    @Test
    public void productCategoryFormat(){
        System.out.println("Format Category u Product:");
        try {
            System.out.println("Nutno zkontrolovat, spravnost chyby:");
            String msg = validator.validate("productcategoryformatcompany.xml");
            System.out.println(msg);
            System.out.println();
            assertNotNull("productcategoryformatcompany.xml obsahuje chybu.",msg);
            msg = validator.validate("productcategoryformatcompany1.xml");
            System.out.println(msg);
            System.out.println();
            assertNotNull("productcategoryformatcompany1.xml obsahuje chybu.",msg);
            msg = validator.validate("productcategoryformatcompany2.xml");
            System.out.println(msg);
            System.out.println();
            assertNotNull("productcategoryformatcompany2.xml obsahuje chybu.",msg);
         } catch (IOException ex) {
            Logger.getLogger(SchemaTests.class.getName()).log(Level.SEVERE, null, ex);
            fail("Chyba pri validaci productcategoryformatcompany.xml:\n " +ex.getMessage());
        }
    }
    
    @Test
    public void productNameMandatory(){
        System.out.println("Format Name u Product:");
        try {
            System.out.println("Nutno zkontrolovat, spravnost chyby:");
            String msg = validator.validate("productnameformatcompany.xml");
            System.out.println(msg);
            System.out.println();
            assertNotNull("productnameformatcompany.xml obsahuje chybu.",msg);
         } catch (IOException ex) {
            Logger.getLogger(SchemaTests.class.getName()).log(Level.SEVERE, null, ex);
            fail("Chyba pri validaci productnameformatcompany.xml:\n " +ex.getMessage());
        }
    }
    
    @Test
    public void productNameFormat(){
        System.out.println("Format Name u Product:");
        try {
            System.out.println("Nutno zkontrolovat, spravnost chyby:");
            String msg = validator.validate("productnameformatcompany.xml");
            System.out.println(msg);
            System.out.println();
            assertNotNull("productnameformatcompany.xml obsahuje chybu.",msg);
         } catch (IOException ex) {
            Logger.getLogger(SchemaTests.class.getName()).log(Level.SEVERE, null, ex);
            fail("Chyba pri validaci productnameformatcompany.xml:\n " +ex.getMessage());
        }
    }
    
    @Test
    public void productCostMandatory(){
        System.out.println("Chybejici Cost u Product:");
        try {
            System.out.println("Nutno zkontrolovat, spravnost chyby:");
            String msg = validator.validate("productcostmissingcompany.xml");
            System.out.println(msg);
            System.out.println();
            assertNotNull("productcostmissingcompany.xml obsahuje chybu.",msg);
         } catch (IOException ex) {
            Logger.getLogger(SchemaTests.class.getName()).log(Level.SEVERE, null, ex);
            fail("Chyba pri validaci productcostmissingcompany.xml:\n " +ex.getMessage());
        }
    } 
    
    @Test
    public void productCostValue(){
        System.out.println("Spatna Cost u Product:");
        try {
            System.out.println("Nutno zkontrolovat, spravnost chyby:");
            String msg = validator.validate("productcostvaluecompany.xml");
            System.out.println(msg);
            System.out.println();
            assertNotNull("productcostvaluecompany.xml obsahuje chybu.",msg);
            msg = validator.validate("productcostvaluecompany1.xml");
            System.out.println(msg);
            System.out.println();
            assertNotNull("productcostvaluecompany1.xml obsahuje chybu.",msg);
         } catch (IOException ex) {
            Logger.getLogger(SchemaTests.class.getName()).log(Level.SEVERE, null, ex);
            fail("Chyba pri validaci productcostvaluecompany.xml:\n " +ex.getMessage());
        }
    }
            
    @Test
    public void productPriceMandatory(){
        System.out.println("Chybejici Price u Product:");
        try {
            System.out.println("Nutno zkontrolovat, spravnost chyby:");
            String msg = validator.validate("missingpricecompany.xml");
            System.out.println(msg);
            System.out.println();
            assertNotNull("missingpricecompany.xml obsahuje chybu.",msg);
            
         } catch (IOException ex) {
            Logger.getLogger(SchemaTests.class.getName()).log(Level.SEVERE, null, ex);
            fail("Chyba pri validaci productcostvaluecompany.xml:\n " +ex.getMessage());
        }
    }
    
    @Test
    public void productPriceValue(){
        System.out.println("Spatna Price u Product:");
        try {
            System.out.println("Nutno zkontrolovat, spravnost chyby:");
            String msg = validator.validate("productpricevaluecompany.xml");
            System.out.println(msg);
            System.out.println();
            assertNotNull("productpricevaluecompany.xml obsahuje chybu.",msg);
            msg = validator.validate("productpricevaluecompany1.xml");
            System.out.println(msg);
            System.out.println();
            assertNotNull("productpricevaluecompany1.xml obsahuje chybu.",msg);
         } catch (IOException ex) {
            Logger.getLogger(SchemaTests.class.getName()).log(Level.SEVERE, null, ex);
            fail("Chyba pri validaci productcostvaluecompany.xml:\n " +ex.getMessage());
        }
    }
    
    @Test
    public void serviceDescription(){
        System.out.println("Chybejici Description u Product (Sluzba):");
        try {
            System.out.println("Nutno zkontrolovat, spravnost chyby:");
            String msg = validator.validate("missingdescriptioncompany.xml");
            System.out.println(msg);
            System.out.println();
            assertNotNull("missingdescriptioncompany.xml obsahuje chybu.",msg);
            
         } catch (IOException ex) {
            Logger.getLogger(SchemaTests.class.getName()).log(Level.SEVERE, null, ex);
            fail("Chyba pri validaci productcostvaluecompany.xml:\n " +ex.getMessage());
        }
    }
   
    @Test
    public void serviceMissingPriceAndCost(){
        System.out.println("Chybejici Price a Costs u Product (Sluzba):");
        try {
            System.out.println("Nutno zkontrolovat, spravnost chyby:");
            String msg = validator.validate("missingpriceandcostscompany.xml");
            System.out.println(msg);
            System.out.println();
            assertNotNull("missingpriceandcostscompany.xml obsahuje chybu.",msg);
            
         } catch (IOException ex) {
            Logger.getLogger(SchemaTests.class.getName()).log(Level.SEVERE, null, ex);
            fail("Chyba pri validaci priceandcostscompany.xml:\n " +ex.getMessage());
        }
    }
    
    @Test
    public void servicePrice(){
        System.out.println("Spatna Price u Product(Service):");
        try {
            System.out.println("Nutno zkontrolovat, spravnost chyby:");
            String msg = validator.validate("servicepricevaluecompany.xml");
            System.out.println(msg);
            System.out.println();
            assertNotNull("servicepricevaluecompany.xml obsahuje chybu.",msg);
            msg = validator.validate("servicepricevaluecompany1.xml");
            System.out.println(msg);
            System.out.println();
            assertNotNull("servicepricevaluecompany1.xml obsahuje chybu.",msg);
         } catch (IOException ex) {
            Logger.getLogger(SchemaTests.class.getName()).log(Level.SEVERE, null, ex);
            fail("Chyba pri validaci servicepricevaluecompany[1].xml:\n " +ex.getMessage());
        }
    }
    
    @Test
    public void serviceCosts(){
        System.out.println("Spatna Cost u Product:");
        try {
            System.out.println("Nutno zkontrolovat, spravnost chyby:");
            String msg = validator.validate("servicecostvaluecompany.xml");
            System.out.println(msg);
            System.out.println();
            assertNotNull("servicecostvaluecompany.xml obsahuje chybu.",msg);
            msg = validator.validate("servicecostvaluecompany1.xml");
            System.out.println(msg);
            System.out.println();
            assertNotNull("servicecostvaluecompany1.xml obsahuje chybu.",msg);
         } catch (IOException ex) {
            Logger.getLogger(SchemaTests.class.getName()).log(Level.SEVERE, null, ex);
            fail("Chyba pri validaci servicecostvaluecompany[1].xml:\n " +ex.getMessage());
        }
    }
    
    private List<String> loadValidDocumentsNames() {
        String correctFilenamesPropety = settings.getProperty("correctfiles");
        if(correctFilenamesPropety == null) return new LinkedList<>();
        String[] correctFileNames = correctFilenamesPropety.split(" ");
        return Arrays.asList(correctFileNames);
    }

    
}
