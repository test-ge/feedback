/**
 *
 */
package fr.ge.feedback.core.support.appstatus;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Properties;

import org.junit.Test;

import fr.ge.feedback.core.support.appstatus.PropertyProvider;

/**
 * Class PropertyProviderTest.
 *
 * @author Christian Cougourdan
 */
public class PropertyProviderTest {

    /**
     * Test simple.
     *
     * @throws Exception          exception
     */
    @Test
    public void testSimple() throws Exception {
        final Properties properties = new Properties();
        properties.setProperty("prop01", "value 01");
        properties.setProperty("prop02", "value 02");
        properties.setProperty("prop03.password", "value 03.1");
        properties.setProperty("prop03.passwd", "value 03.2");
        properties.setProperty("prop03.pwd", "value 03.3");

        final PropertyProvider provider = new PropertyProvider();
        provider.setProperties(properties);

        assertEquals("Configuration", provider.getCategory());
        assertThat(provider.getProperties().entrySet(), hasSize(5));
        assertThat( //
                provider.getProperties(), //
                allOf( //
                        hasEntry("prop01", "value 01"), //
                        hasEntry("prop02", "value 02"), //
                        hasEntry("prop03.password", "********"), //
                        hasEntry("prop03.passwd", "********"), //
                        hasEntry("prop03.pwd", "********") //
                ) //
        );
    }

    /**
     * Test no properties.
     *
     * @throws Exception          exception
     */
    @Test
    public void testNoProperties() throws Exception {
        final PropertyProvider provider = new PropertyProvider();
        provider.setProperties(null);

        assertEquals("Configuration", provider.getCategory());
        assertThat(provider.getProperties().entrySet(), hasSize(0));
    }

    /**
     * Test empty.
     *
     * @throws Exception          exception
     */
    @Test
    public void testEmpty() throws Exception {
        final PropertyProvider provider = new PropertyProvider();
        provider.setProperties(new Properties());

        assertEquals("Configuration", provider.getCategory());
        assertThat(provider.getProperties().entrySet(), hasSize(0));
    }

}
