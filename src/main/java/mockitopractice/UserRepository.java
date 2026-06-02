package mockitopractice;

/**
 * Simulates a database repository for User objects.
 *
 * In real applications this would connect to a database.
 * In tests, this class is replaced with a Mock object so no real DB is needed.
 */
public class UserRepository {

    public User findById(int id) {
        // real implementation would query the database
        throw new UnsupportedOperationException("Real DB not available in tests -- use a Mock");
    }

    public void save(User user) {
        // real implementation would insert/update the database
        throw new UnsupportedOperationException("Real DB not available in tests -- use a Mock");
    }

    public boolean existsByEmail(String email) {
        // real implementation would query the database
        throw new UnsupportedOperationException("Real DB not available in tests -- use a Mock");
    }
}
