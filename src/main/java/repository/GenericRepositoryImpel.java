package repository;

public class GenericRepositoryImpel<K> {

    private final Connection connection = new Connection();


    public K add(K entity) {
        try (var session = connection.openCurrentSessionWithTransaction()){
            var transaction = connection.getCurrentTransaction();
            try {
                session.save(entity);
                transaction.commit();
                //connection.closeCurrentSessionWithTransaction();
                return entity;
            } catch (Exception e) {
                transaction.rollback();
                System.out.println("Insert not successful!");
                return null;
            }
        }
    }

    public void update(K entity) {
        try (var session = connection.openCurrentSessionWithTransaction()) {
            var transaction = connection.getCurrentTransaction();
            try {
                session.update(entity);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
            }
        }
    }

    public void delete(K entity) {
        try (var session = connection.openCurrentSessionWithTransaction()) {
            var transaction = connection.getCurrentTransaction();
            try {
                session.delete(entity);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
            }
        }

    }

}
