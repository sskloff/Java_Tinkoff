package edu.project4.Models;

import edu.project4.Variations.DiscVariation;
import edu.project4.Variations.HeartVariation;
import edu.project4.Variations.PolarVariation;
import edu.project4.Variations.SinusoidalVariation;
import edu.project4.Variations.SphericalVariation;
import edu.project4.Variations.Variation;
import java.util.Map;

public enum NonlinearVariations {
    HEART,
    SINUSOIDAL,
    DISC,
    SPHERICAL,
    POLAR;

    public static final Map<NonlinearVariations, Variation> MAP = Map.ofEntries(
        Map.entry(NonlinearVariations.HEART, new HeartVariation()),
        Map.entry(NonlinearVariations.SINUSOIDAL, new SinusoidalVariation()),
        Map.entry(NonlinearVariations.DISC, new DiscVariation()),
        Map.entry(NonlinearVariations.SPHERICAL, new SphericalVariation()),
        Map.entry(NonlinearVariations.POLAR, new PolarVariation())
    );
}
