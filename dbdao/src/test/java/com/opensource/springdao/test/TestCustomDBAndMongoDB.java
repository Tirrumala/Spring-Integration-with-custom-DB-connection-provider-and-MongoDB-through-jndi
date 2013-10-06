import org.springframework.data.mongodb.core.MongoTemplate;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;

@Component
public class TestCustomDBAndMongoDB {
	@Resource(name = "springDaoConnectionProvider")
	private ConnectionProvider connectionProvider;
	
	
}