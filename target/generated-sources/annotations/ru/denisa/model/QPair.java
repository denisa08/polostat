package ru.denisa.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPair is a Querydsl query type for Pair
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPair extends EntityPathBase<Pair> {

    private static final long serialVersionUID = -2092516648L;

    public static final QPair pair = new QPair("pair");

    public final NumberPath<Double> allTimeChanged = createNumber("allTimeChanged", Double.class);

    public final NumberPath<Double> allTimeChangedVolume = createNumber("allTimeChangedVolume", Double.class);

    public final NumberPath<Double> baseVolume = createNumber("baseVolume", Double.class);

    public final NumberPath<Double> close = createNumber("close", Double.class);

    public final NumberPath<Long> date = createNumber("date", Long.class);

    public final NumberPath<Double> fiveChanged = createNumber("fiveChanged", Double.class);

    public final NumberPath<Double> fiveChangedVolume = createNumber("fiveChangedVolume", Double.class);

    public final NumberPath<Double> fourChanged = createNumber("fourChanged", Double.class);

    public final NumberPath<Double> fourChangedVolume = createNumber("fourChangedVolume", Double.class);

    public final NumberPath<Double> high = createNumber("high", Double.class);

    public final NumberPath<Double> hourChanged = createNumber("hourChanged", Double.class);

    public final NumberPath<Double> hourChangedVolume = createNumber("hourChangedVolume", Double.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Double> last = createNumber("last", Double.class);

    public final NumberPath<Double> low = createNumber("low", Double.class);

    public final StringPath name = createString("name");

    public final NumberPath<Double> oneChanged = createNumber("oneChanged", Double.class);

    public final NumberPath<Double> oneChangedVolume = createNumber("oneChangedVolume", Double.class);

    public final NumberPath<Double> sixHourChanged = createNumber("sixHourChanged", Double.class);

    public final NumberPath<Double> sixHourChangedVolume = createNumber("sixHourChangedVolume", Double.class);

    public final NumberPath<Double> tenChanged = createNumber("tenChanged", Double.class);

    public final NumberPath<Double> tenChangedVolume = createNumber("tenChangedVolume", Double.class);

    public final NumberPath<Double> thirtyChanged = createNumber("thirtyChanged", Double.class);

    public final NumberPath<Double> thirtyChangedVolume = createNumber("thirtyChangedVolume", Double.class);

    public final NumberPath<Double> threeChanged = createNumber("threeChanged", Double.class);

    public final NumberPath<Double> threeChangedVolume = createNumber("threeChangedVolume", Double.class);

    public final NumberPath<Double> threeHourChanged = createNumber("threeHourChanged", Double.class);

    public final NumberPath<Double> threeHourChangedVolume = createNumber("threeHourChangedVolume", Double.class);

    public final NumberPath<Double> twentyHourChanged = createNumber("twentyHourChanged", Double.class);

    public final NumberPath<Double> twentyHourChangedVolume = createNumber("twentyHourChangedVolume", Double.class);

    public final NumberPath<Double> twoChanged = createNumber("twoChanged", Double.class);

    public final NumberPath<Double> twoChangedVolume = createNumber("twoChangedVolume", Double.class);

    public final NumberPath<Double> volume = createNumber("volume", Double.class);

    public final NumberPath<Double> weightedAverage = createNumber("weightedAverage", Double.class);

    public QPair(String variable) {
        super(Pair.class, forVariable(variable));
    }

    public QPair(Path<? extends Pair> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPair(PathMetadata metadata) {
        super(Pair.class, metadata);
    }

}

