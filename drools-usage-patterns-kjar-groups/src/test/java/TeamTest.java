import com.github.javafaker.Faker;
import com.rhc.drools.example.persistence.Name;
import com.rhc.drools.example.persistence.Person;
import com.rhc.drools.example.persistence.Team;
import org.drools.core.event.DebugRuleRuntimeEventListener;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by srang on 8/3/16.
 */
public class TeamTest {

    private static final KieServices KIE_SERVICES = KieServices.Factory.get();
    Faker faker = new Faker();
    private KieSession kieSession;


    StringBuffer logger = new StringBuffer();

    @Before
    public void setUp() throws Exception {

        KieContainer KIE_CONTAINER = KIE_SERVICES.newKieClasspathContainer();
        KieBase kieBase = KIE_CONTAINER.getKieBase();
        kieSession = kieBase.newKieSession();
        //Start the polling every POLLING_INTERVAL milliseconds
        kieSession.addEventListener(new DebugRuleRuntimeEventListener());
        kieSession.addEventListener(new DebugAgendaEventListener());


    }

    @After
    public void tearDown() throws Exception {

        kieSession.dispose();

    }

    @Test
    public void testMedicareDeductibleAmount() throws ParseException {
        Team team = new Team(faker.team().name());
        for (int i = 0; i<20; i++) {
            team.addMember(new Person(new Name(faker.name().firstName(), faker.name().lastName()), faker.number().numberBetween(14,50)));
        }
        team.addMember(new Person(new Name("Sam","Rang"),34));

        kieSession.insert(team);
        kieSession.fireAllRules();
        assertThat("team has problems", !team.getProblems().isEmpty());
    }

}
