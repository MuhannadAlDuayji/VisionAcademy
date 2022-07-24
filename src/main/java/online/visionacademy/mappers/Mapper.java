package online.visionacademy.mappers;

public interface Mapper <T,E>{

    public abstract E mapToDTO(T entity);
    public abstract T mapToEntity(E entityDTO);

}
