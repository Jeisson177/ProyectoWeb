package Services;

import java.util.Collection;

import com.example.demo.entity.Producto;

public interface ProductoTablaService {
    
    public Producto SearchById(int id);

    public Collection<Producto> SearchAll();
}
