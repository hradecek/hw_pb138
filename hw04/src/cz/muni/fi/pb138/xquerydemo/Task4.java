/*
 * Task 4: XQuery
 * 
 * Write a XQuery query and place it into the sales.xq file.
 * 
 * The query should produce to the console (System.out) 
 * an output similar to "expected-output.xml" file
 * when the main method is launched.
 * 
 * So, for each element <shop> in the books.xml produce
 * one element <shop> within the root element <sales>.
 * 
 * This <shop> element should have the attribute "name"
 * with the name of the shop (same as in the input -- books.xml).
 * Additionally, the <shop> element should contain child 
 * elements <book> for each book title that the shop has sold.
 * 
 * For each of these <book> elements should be empty 
 * (no children) but should have four attributes:
 * 
 * amount -- the number of books with this book title sold at this shop
 * title -- the title of this book
 * price -- the price of this book in this shop
 * turnover -- the total sum of money earned from sales of this book
 * (it equals amount * price)
 * 
 * Hint 1: You will find a template of the solution in sales.xq
 * 
 * Hint 2: The solution leads to the use of two nested for loops:
 * the outer one iterates over the shops and produces the <shop> elements,
 * the inner one iterates (for the concrete shop from the outer loop)
 * over all books sold at this shop. 
 * 
 */
package cz.muni.fi.pb138.xquerydemo;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.stream.StreamResult;
import net.sf.saxon.Configuration;
import net.sf.saxon.query.DynamicQueryContext;
import net.sf.saxon.query.StaticQueryContext;
import net.sf.saxon.query.XQueryExpression;
import net.sf.saxon.trans.XPathException;

public class Task4 {

    public static void main(String[] args) throws XPathException, IOException {

        Configuration config = new Configuration();

        StaticQueryContext staticContext = config.newStaticQueryContext();

        XQueryExpression exp = staticContext.compileQuery(new FileReader("sales.xq"));
        
        DynamicQueryContext dynamicContext =
                new DynamicQueryContext(config);

        Properties props = new Properties();
        props.setProperty(OutputKeys.METHOD, "xml");
        props.setProperty(OutputKeys.INDENT, "yes");

        exp.run(dynamicContext, new StreamResult("output.xml"), props);
        exp.run(dynamicContext, new StreamResult(System.out), props);
        
    }
}
