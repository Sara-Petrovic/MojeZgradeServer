package rs.ac.bg.fon.nprog.zgradeserver.operation.mesto;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
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
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import rs.ac.bg.fon.nprog.zgradeserver.repository.Repository;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.Korisnik;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.Mesto;


@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class UcitajListuMestaOperacijaTest {

	private UcitajListuMestaOperacija operacija;
	
	@Mock
    Repository mockedRepository; 
	
	@Before
	public void setUp() throws Exception {
		operacija = new UcitajListuMestaOperacija();
	}

	@After
	public void tearDown() throws Exception {
		operacija= null;
	}

	@Test
	public void testPreconditionsNull() {
		assertThrows(java.lang.Exception.class,()->operacija.executeOperation(null));
	}

	@Test
	public void testPreconditionsNotClassMesto() {
		assertThrows(java.lang.Exception.class,()->operacija.executeOperation(new Korisnik()));
	}
	@Test
	public void testExecuteOperation() throws Exception {
		//lista mesta koju vraca repository:
		List<Mesto> listaMesta = new ArrayList<Mesto>();
		listaMesta.add(new Mesto(1l, "11000", "Beograd"));
		
		assertNotNull(mockedRepository);
		
        Mockito.when(mockedRepository.getAll( new Mesto())).thenReturn(listaMesta);
        operacija.setRepository(mockedRepository);

       operacija.executeOperation(new Mesto());
       List<Mesto> mesta = operacija.getMesta();

        // Check the result
        assertNotNull(mesta);
        Mesto mesto = mesta.get(0);
        
        assertNotNull(mesto);
        assertEquals(new Mesto(1l, "11000", "Beograd"), mesto);
        assertEquals(1, mesta.size());
	}

}
