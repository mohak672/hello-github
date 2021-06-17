public class Service {
    private Database database;

    public Service(Database database){
        this.database = database;
    }

    public boolean query(String query) {
        return database.isAvailable();
    }

    public String toString(){
        return "Using database with id: "+this.database.getUniqueId();
    }
}

class Database {

    public boolean isAvailable(){
        // TODO implement the access to the database
        return false;
    }

    public int getUniqueId(){
        return 42;
    }
}
