package rs.ac.bg.fon.nprog.zgradeserver.operation.vlasnikposebnogdela;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

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

import rs.ac.bg.fon.nprog.zgradeserver.operation.stambenazajednica.ZapamtiStambenuZajednicuOperacija;
import rs.ac.bg.fon.nprog.zgradeserver.repository.Repository;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.Korisnik;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.StambenaZajednica;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.VlasnikPosebnogDela;


	@ExtendWith(MockitoExtension.class)
	@RunWith(MockitoJUnitRunner.class)
	public class ZapamtiVlasnikaPosebnogDelaOperacijaTest {

		private ZapamtiVlasnikaPosebnogDelaOperacija operacija;
		
		@Mock
	    Repository mockedRepository; 
		
		@Before
		public void setUp() throws Exception {
			operacija = new ZapamtiVlasnikaPosebnogDelaOperacija();
		}

		@After
		public void tearDown() throws Exception {
			operacija= null;
		}

		@Test
		public void testPreconditionsNull() {
			assertThrows(java.lang.Exception.class,()->operacija.preconditions(null));
		}

		@Test
		public void testPreconditionsNotClassVlasnikPosebnogDela() {
			assertThrows(java.lang.Exception.class,()->operacija.preconditions(new Korisnik()));
		}
		@Test
		public void testExecuteOperation() throws Exception {
			VlasnikPosebnogDela v = new VlasnikPosebnogDela();
			
			v.setId(1l);
			
			assertNotNull(mockedRepository);
			Mockito.doAnswer(new Answer<Void>() {
			    public Void answer(InvocationOnMock invocation) {
			      Object[] args = invocation.getArguments();
			      System.out.println("called with arguments: " + Arrays.toString(args));
			      return null;
			    }
			}).when(mockedRepository).edit(v);
	        operacija.setRepository(mockedRepository);

	       operacija.executeOperation(v);

	        // Check the result
	        assertNotNull(v);
			

		}
		

	}
