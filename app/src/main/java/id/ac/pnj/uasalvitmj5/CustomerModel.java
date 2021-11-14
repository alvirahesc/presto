package id.ac.pnj.uasalvitmj5;

public class CustomerModel {

    //properti objek
    private int id;
    private String name;
    private int age;
    private boolean isActive;

    //constructor, dipanggil saat instance baru dari objek dibuat (diminta langsung nanti)

    public CustomerModel(int id, String name, int age, boolean isActive) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isActive = isActive;
    }
    //karena di atas ada 4 parameter, kadang app minta constructor nonparam. Kemungkinan tidak terpakai constructor yang baru dibuat ini, tapi untuk jaga-jaga saja.
    public CustomerModel() {
    }

    //toString dibutuhkan untuk menuliskan konten dari class object


    @Override
    public String toString() {
        return "CustomerModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", isActive=" + isActive +
                '}';
    }

    //Getter dan setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
