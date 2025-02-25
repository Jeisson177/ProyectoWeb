package Services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Producto;
import com.example.demo.repository.ProductoRepository;

@Service
public class ProductoTablaServicioImp implements ProductoTablaService{

    @Autowired
    private ProductoRepository Repo;

    @Override
    public Producto SearchById(int id) {
        return Repo.getProducto(id);
    }

    @Override
    public Collection<Producto> SearchAll() {
        return Repo.getAllProductos();
    }


    
}
