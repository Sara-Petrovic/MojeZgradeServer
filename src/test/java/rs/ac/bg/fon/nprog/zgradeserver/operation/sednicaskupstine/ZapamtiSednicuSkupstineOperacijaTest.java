package rs.ac.bg.fon.nprog.zgradeserver.operation.sednicaskupstine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.SednicaSkupstine;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class ZapamtiSednicuSkupstineOperacijaTest {

	private ZapamtiSednicuSkupstineOperacija operacija;

	@Mock
	Repository mockedRepository;

	@Before
	public void setUp() throws Exception {
		operacija = new ZapamtiSednicuSkupstineOperacija();
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
	public void testPreconditionsNotClassSednicaSkupstine() {
		assertThrows(java.lang.Exception.class, () -> operacija.preconditions(new Mesto()));
	}

	@Test
	public void testExecuteOperation() throws Exception {
		SednicaSkupstine ss = new SednicaSkupstine();
		
		SednicaSkupstine added= ss;
		
		ss.setId(1l);
		
		assertNotNull(mockedRepository);
		Mockito.doAnswer(new Answer<Void>() {
		    public Void answer(InvocationOnMock invocation) {
		      Object[] args = invocation.getArguments();
		      System.out.println("called with arguments: " + Arrays.toString(args));
		      return null;
		    }
		}).when(mockedRepository).edit(ss);
        operacija.setRepository(mockedRepository);

       operacija.executeOperation(ss);

        // Check the result
        assertNotNull(ss);
        System.out.println(ss);
        
	}
}