package rs.ac.bg.fon.nprog.zgradeserver.operation.stambenazajednica;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import rs.ac.bg.fon.nprog.zgradeserver.repository.Repository;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.Mesto;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.StambenaZajednica;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class ObrisiStambenuZajednicuOperacijaTest {


	private ObrisiStambenuZajednicuOperacija operacija;

	@Mock
	Repository mockedRepository;

	@Before
	public void setUp() throws Exception {
		operacija = new ObrisiStambenuZajednicuOperacija();
	}

	@After
	public void tearDown() throws Exception {
		operacija = null;
	}

	@Test
	public void testPreconditionsNull() {
		assertThrows(java.lang.Exception.class, () -> operacija.preconditions(null));
	}

	@Test
	public void testPreconditionsNotClassStambenaZajednica() {
		assertThrows(java.lang.Exception.class, () -> operacija.preconditions(new Mesto()));
	}

	@Test
	public void testExecuteOperation() throws Exception {

		StambenaZajednica sz = new StambenaZajednica();
			
		sz.setId(1l);
		
		assertNotNull(mockedRepository);
		Mockito.doAnswer(new Answer<Void>() {
		    public Void answer(InvocationOnMock invocation) {
		      Object[] args = invocation.getArguments();
		      System.out.println("called with arguments: " + Arrays.toString(args));
		      return null;
		    }
		}).when(mockedRepository).delete(sz);
        operacija.setRepository(mockedRepository);

       operacija.executeOperation(sz);

        // Check the result
        assertNotNull(sz);
        System.out.println(sz);

	}
	

}
