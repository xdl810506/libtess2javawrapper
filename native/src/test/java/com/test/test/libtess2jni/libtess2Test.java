/*
 * libtess2Test.java
 */

package com.test.test.libtess2jni;

import com.test.utils.test.libtess2jni.libtess2;
import com.test.utils.test.libtess2jni.libtess2.TESStesselator;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author xdl810506
 *
 */
public class libtess2Test {
    @Test
    public void test() {
        testBasic2dBoxTriangulation();
        testBasic3dCubeTriangulation();
        testBasic2dBoxBoundary();
    }

    public void testBasic2dBoxBoundary() {
        final TESStesselator tess = libtess2.tessNewTess();
        libtess2.tessAddContour(tess, 2, new float[]{ 0, 0, 0, 1, 1, 1, 1, 0 }, 8, 4, 0);
        libtess2.tessTesselate(tess, libtess2.TessWindingRule.TESS_WINDING_ODD,
                libtess2.TessElementType.TESS_BOUNDARY_CONTOURS, 3, 2, null);

        final String expectedResult =
                "1 [0, 4] 4 [3, 0, 1, 2] [1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0]";
        checkExpectedResult(tess, expectedResult);
    }

    public void testBasic2dBoxTriangulation() {
        final TESStesselator tess = libtess2.tessNewTess();
        libtess2.tessAddContour(tess, 2, new float[]{ 0, 0, 0, 1, 1, 1, 1, 0 }, 8, 4, 0);
        libtess2.tessTesselate(tess, libtess2.TessWindingRule.TESS_WINDING_ODD,
                libtess2.TessElementType.TESS_POLYGONS,
                3, 2, null);

        final String expectedResult =
                "2 [0, 1, 2, 1, 0, 3] 4 [0, 2, 3, 1] [0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 1.0]";
        checkExpectedResult(tess, expectedResult);
    }

    public void testBasic3dCubeTriangulation() {
        final TESStesselator tess = libtess2.tessNewTess();
        libtess2.tessAddContour(tess, 3, new float[]{ 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0 }, 12, 4,
                0);
        libtess2.tessTesselate(tess, libtess2.TessWindingRule.TESS_WINDING_ODD,
                libtess2.TessElementType.TESS_POLYGONS,
                3, 3, null);

        final String expectedResult =
                "2 [0, 1, 2, 1, 0, 3] 4 [0, 2, 3, 1] [0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0]";
        checkExpectedResult(tess, expectedResult);
    }

    private void checkExpectedResult(final TESStesselator tess,
            final String expectedResult) {
        final int elementCount = libtess2.tessGetElementCount(tess);
        final int[] elements = libtess2.tessGetElements(tess);
        final int vertexCount = libtess2.tessGetVertexCount(tess);
        final int[] vertexIndices = libtess2.tessGetVertexIndices(tess);
        final float[] vertices = libtess2.tessGetVertices(tess);

        final String result = elementCount + " " + Arrays.toString(elements) + " " + vertexCount +
                " " +
                Arrays.toString(vertexIndices) + " " + Arrays.toString(vertices);

        Assert.assertTrue(expectedResult.equals(result));
    }
}
