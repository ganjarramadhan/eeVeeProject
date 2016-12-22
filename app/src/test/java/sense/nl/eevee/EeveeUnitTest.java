package sense.nl.eevee;

import org.junit.Test;

import sense.nl.eevee.util.EeveeUtil;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class EeveeUnitTest {

    @Test
    public void emailValidator_CorrectEmailSimple_ReturnsTrue() {
        assertTrue(EeveeUtil.checkEmail("ganjar@ramadhan.com"));
    }

    @Test
    public void emailValidator_CorrectEmailSubDomain_ReturnsTrue() {
        assertTrue(EeveeUtil.checkEmail("ganjar@ramadhan.co.id"));
    }

    @Test
    public void emailValidator_InvalidEmailNoTld_ReturnsFalse() {
        assertFalse(EeveeUtil.checkEmail("ganjar@ramadhan"));
    }

    @Test
    public void emailValidator_InvalidEmailDoubleDot_ReturnsFalse() {
        assertFalse(EeveeUtil.checkEmail("ganjar@ramadhan..com"));
    }

    @Test
    public void emailValidator_InvalidEmailNoUsername_ReturnsFalse() {
        assertFalse(EeveeUtil.checkEmail("@ramadhan.com"));
    }

    @Test
    public void emailValidator_EmptyString_ReturnsFalse() {
        assertFalse(EeveeUtil.checkEmail(""));
    }

    @Test
    public void emailValidator_NullEmail_ReturnsFalse() {
        assertFalse(EeveeUtil.checkEmail(null));
    }

}