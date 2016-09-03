package entidades;

import entidades.Produto;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-09-02T21:18:42")
@StaticMetamodel(Carrinho.class)
public class Carrinho_ { 

    public static volatile ListAttribute<Carrinho, Produto> produtos;
    public static volatile SingularAttribute<Carrinho, Double> valor;
    public static volatile SingularAttribute<Carrinho, Long> id;
    public static volatile SingularAttribute<Carrinho, Integer> qtdeItens;

}