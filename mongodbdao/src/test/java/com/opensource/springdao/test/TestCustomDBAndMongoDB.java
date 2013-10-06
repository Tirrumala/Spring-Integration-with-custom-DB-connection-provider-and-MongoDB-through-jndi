import org.springframework.data.mongodb.core.MongoTemplate;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;

@Component
public class TestCustomDBAndMongoDB {
	@Autowired
    public MongoTemplate mongoTemplate;

	
}