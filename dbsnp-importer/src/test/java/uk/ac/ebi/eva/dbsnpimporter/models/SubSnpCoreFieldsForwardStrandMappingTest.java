/*
 * Copyright 2017 EMBL - European Bioinformatics Institute
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.ac.ebi.eva.dbsnpimporter.models;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import uk.ac.ebi.eva.dbsnpimporter.exception.UndefinedHgvsAlleleException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Tests the mapping of alleles in a SubSnpCoreFields object to the forward strand. Please note that HGVS strings have
 * been manually generated and may not be fully complaint, but this doesn't affect the correctness of the tests.
 */
public class SubSnpCoreFieldsForwardStrandMappingTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void snpAllelesInForwardStrandMustNotChange() throws Exception {
        SubSnpCoreFields subSnpCoreFields1 = new SubSnpCoreFields(26201546,
                                                                  Orientation.FORWARD, 13677177L,
                                                                  Orientation.FORWARD,
                                                                  "NT_455866.1",
                                                                  1766472L,
                                                                  1766472L,
                                                                  Orientation.FORWARD,
                                                                  LocusType.SNP,
                                                                  "4",
                                                                  91223961L,
                                                                  91223961L,
                                                                  "T", "T", "A", "T/A",
                                                                  "NC_006091.4:g.91223961T>A", 91223961L, 91223961L,
                                                                  Orientation.FORWARD,
                                                                  "NT_455866.1:g.1766472T>A", 1766472L, 1766472L,
                                                                  Orientation.FORWARD, null, null, "batch");

        assertEquals("T", subSnpCoreFields1.getReferenceInForwardStrand());
        assertEquals("A", subSnpCoreFields1.getAlternateInForwardStrand());
        assertEquals("T/A", subSnpCoreFields1.getAllelesInForwardStrand());
    }

    @Test
    public void insertionAllelesInForwardStrandMustNotChange() throws Exception {
        // Insertion with non-empty alleles
        SubSnpCoreFields subSnpCoreFields1 = new SubSnpCoreFields(26201546,
                                                                  Orientation.FORWARD, 13677177L,
                                                                  Orientation.FORWARD,
                                                                  "NT_455866.1",
                                                                  1766472L,
                                                                  1766472L,
                                                                  Orientation.FORWARD,
                                                                  LocusType.INSERTION,
                                                                  "4",
                                                                  91223961L,
                                                                  91223961L,
                                                                  "T", "T", "TAGA", "T/TAGA",
                                                                  "NC_006091.4:g.91223962insAGA", 91223961L, 91223961L,
                                                                  Orientation.FORWARD,
                                                                  "NT_455866.1:g.1766473insAGA", 1766472L, 1766472L,
                                                                  Orientation.FORWARD, null, null, "batch");

        assertEquals("T", subSnpCoreFields1.getReferenceInForwardStrand());
        assertEquals("TAGA", subSnpCoreFields1.getAlternateInForwardStrand());

        // Insertion with dash in reference
        SubSnpCoreFields subSnpCoreFields2 = new SubSnpCoreFields(26201546,
                                                                  Orientation.FORWARD, 13677177L,
                                                                  Orientation.FORWARD,
                                                                  "NT_455866.1",
                                                                  1766472L,
                                                                  1766472L,
                                                                  Orientation.FORWARD,
                                                                  LocusType.INSERTION,
                                                                  "4",
                                                                  91223961L,
                                                                  91223961L,
                                                                  "-", "-", "TA", "-/TA",
                                                                  "NC_006091.4:g.91223962insA", 91223961L, 91223961L,
                                                                  Orientation.FORWARD,
                                                                  "NT_455866.1:g.1766473insA", 1766472L, 1766472L,
                                                                  Orientation.FORWARD, null, null, "batch");

        assertEquals("", subSnpCoreFields2.getReferenceInForwardStrand());
        assertEquals("TA", subSnpCoreFields2.getAlternateInForwardStrand());

        // Insertion with null reference
        SubSnpCoreFields subSnpCoreFields3 = new SubSnpCoreFields(26201546,
                                                                  Orientation.FORWARD, 13677177L,
                                                                  Orientation.FORWARD,
                                                                  "NT_455866.1",
                                                                  1766472L,
                                                                  1766472L,
                                                                  Orientation.FORWARD,
                                                                  LocusType.INSERTION,
                                                                  "4",
                                                                  91223961L,
                                                                  91223961L,
                                                                  null, null, "TA", "-/TA",
                                                                  "NC_006091.4:g.91223962insA", 91223961L, 91223961L,
                                                                  Orientation.FORWARD,
                                                                  "NT_455866.1:g.1766473insA", 1766472L, 1766472L,
                                                                  Orientation.FORWARD, null, null, "batch");

        assertEquals("", subSnpCoreFields3.getReferenceInForwardStrand());
        assertEquals("TA", subSnpCoreFields3.getAlternateInForwardStrand());
    }

    @Test
    public void deletionAllelesInForwardStrandMustNotChange() throws Exception {
        // Deletion with non-empty alleles
        SubSnpCoreFields subSnpCoreFields1 = new SubSnpCoreFields(26201546,
                                                                  Orientation.FORWARD, 13677177L,
                                                                  Orientation.FORWARD,
                                                                  "NT_455866.1",
                                                                  1766472L,
                                                                  1766472L,
                                                                  Orientation.FORWARD,
                                                                  LocusType.DELETION,
                                                                  "4",
                                                                  91223961L,
                                                                  91223961L,
                                                                  "TAGA", "TAGA", "T", "TAGA/T",
                                                                  "NC_006091.4:g.91223962delAGA", 91223961L, 91223961L,
                                                                  Orientation.FORWARD,
                                                                  "NT_455866.1:g.17664723delAGA", 1766472L, 1766472L,
                                                                  Orientation.FORWARD, null, null, "batch");

        assertEquals("TAGA", subSnpCoreFields1.getReferenceInForwardStrand());
        assertEquals("T", subSnpCoreFields1.getAlternateInForwardStrand());

        // Deletion with dash in alternate
        SubSnpCoreFields subSnpCoreFields2 = new SubSnpCoreFields(26201546,
                                                                  Orientation.FORWARD, 13677177L,
                                                                  Orientation.FORWARD,
                                                                  "NT_455866.1",
                                                                  1766472L,
                                                                  1766472L,
                                                                  Orientation.FORWARD,
                                                                  LocusType.DELETION,
                                                                  "4",
                                                                  91223961L,
                                                                  91223961L,
                                                                  "TA", "TA", "-", "TA/-",
                                                                  "NC_006091.4:g.91223961delTA", 91223961L, 91223961L,
                                                                  Orientation.FORWARD,
                                                                  "NT_455866.1:g.1766472delTA", 1766472L, 1766472L,
                                                                  Orientation.FORWARD, null, null, "batch");

        assertEquals("TA", subSnpCoreFields2.getReferenceInForwardStrand());
        assertEquals("", subSnpCoreFields2.getAlternateInForwardStrand());

        // Deletion with null alternate
        SubSnpCoreFields subSnpCoreFields3 = new SubSnpCoreFields(26201546,
                                                                  Orientation.FORWARD, 13677177L,
                                                                  Orientation.FORWARD,
                                                                  "NT_455866.1",
                                                                  1766472L,
                                                                  1766472L,
                                                                  Orientation.FORWARD,
                                                                  LocusType.DELETION,
                                                                  "4",
                                                                  91223961L,
                                                                  91223961L,
                                                                  "TA", "TA", null, "TA/-",
                                                                  "NC_006091.4:g.91223961delTA", 91223961L, 91223961L,
                                                                  Orientation.FORWARD,
                                                                  "NT_455866.1:g.1766472delTA", 1766472L, 1766472L,
                                                                  Orientation.FORWARD, null, null, "batch");

        assertEquals("TA", subSnpCoreFields3.getReferenceInForwardStrand());
        assertEquals("", subSnpCoreFields3.getAlternateInForwardStrand());
    }

    @Test
    public void allelesInForwardStrandAndNullHgvsCMustNotChange() throws Exception {
        // SNP
        SubSnpCoreFields subSnpCoreFields1 = new SubSnpCoreFields(26201546,
                                                                  Orientation.FORWARD, 13677177L,
                                                                  Orientation.FORWARD,
                                                                  "NT_455866.1",
                                                                  1766472L,
                                                                  1766472L,
                                                                  Orientation.FORWARD,
                                                                  LocusType.SNP,
                                                                  "4",
                                                                  91223961L,
                                                                  91223961L,
                                                                  null, "T", "A", "T/A",
                                                                  null, null, null, Orientation.FORWARD,
                                                                  "NT_455866.1:g.1766472T>A", 1766472L, 1766472L,
                                                                  Orientation.FORWARD, null, null, "batch");

        assertEquals("T", subSnpCoreFields1.getReferenceInForwardStrand());
        assertEquals("A", subSnpCoreFields1.getAlternateInForwardStrand());

        // Insertion
        SubSnpCoreFields subSnpCoreFields2 = new SubSnpCoreFields(26201546,
                                                                  Orientation.FORWARD, 13677177L,
                                                                  Orientation.FORWARD,
                                                                  "NT_455866.1",
                                                                  1766472L,
                                                                  1766472L,
                                                                  Orientation.FORWARD,
                                                                  LocusType.INSERTION,
                                                                  "4",
                                                                  91223961L,
                                                                  91223961L,
                                                                  "-", "-", "TA", "-/TA",
                                                                  null, null, null, Orientation.FORWARD,
                                                                  "NT_455866.1:g.1766473insA", 1766472L, 1766472L,
                                                                  Orientation.FORWARD, null, null, "batch");

        assertEquals("", subSnpCoreFields2.getReferenceInForwardStrand());
        assertEquals("TA", subSnpCoreFields2.getAlternateInForwardStrand());

        // Deletion
        SubSnpCoreFields subSnpCoreFields3 = new SubSnpCoreFields(26201546,
                                                                  Orientation.FORWARD, 13677177L,
                                                                  Orientation.FORWARD,
                                                                  "NT_455866.1",
                                                                  1766472L,
                                                                  1766472L,
                                                                  Orientation.FORWARD,
                                                                  LocusType.DELETION,
                                                                  "4",
                                                                  91223961L,
                                                                  91223961L,
                                                                  "TAGA", "TAGA", "T", "TAGA/T",
                                                                  null, null, null, Orientation.FORWARD,
                                                                  "NT_455866.1:g.1766473delAGA", 1766472L, 1766472L,
                                                                  Orientation.FORWARD, null, null, "batch");

        assertEquals("TAGA", subSnpCoreFields3.getReferenceInForwardStrand());
        assertEquals("T", subSnpCoreFields3.getAlternateInForwardStrand());
    }

    @Test
    public void hgvsCReverseHgvsTForwardStrandMustChange() throws Exception {
        SubSnpCoreFields subSnpCoreFields1 = new SubSnpCoreFields(26201546,
                                                                  Orientation.FORWARD, 13677177L,
                                                                  Orientation.FORWARD,
                                                                  "NT_455866.1",
                                                                  1766472L,
                                                                  1766472L,
                                                                  Orientation.FORWARD,
                                                                  LocusType.INSERTION,
                                                                  "4",
                                                                  91223961L,
                                                                  91223961L,
                                                                  "T", "T", "TAGA", "T/TAGA",
                                                                  "NC_006091.4:g.91223962insAGA", 91223961L, 91223961L,
                                                                  Orientation.REVERSE,
                                                                  "NT_455866.1:g.1766473insAGA", 1766472L, 1766472L,
                                                                  Orientation.FORWARD, null, null, "batch");

        assertEquals("A", subSnpCoreFields1.getReferenceInForwardStrand());
        assertEquals("TCTA", subSnpCoreFields1.getAlternateInForwardStrand());

        // Insertion
        SubSnpCoreFields subSnpCoreFields2 = new SubSnpCoreFields(2018365557,
                                                                  Orientation.FORWARD, 1060492716L,
                                                                  Orientation.FORWARD,
                                                                  "NT_456010.1",
                                                                  107452L,
                                                                  107453L,
                                                                  Orientation.REVERSE,
                                                                  LocusType.INSERTION,
                                                                  "25",
                                                                  89000L,
                                                                  89001L,
                                                                  "-", "-", "G", "-/G",
                                                                  "NC_006112.3:g.88998_88999insC", 88997L, 88998L,
                                                                  Orientation.REVERSE,
                                                                  "NT_456010.1:g.107453_107454insG", 107452L, 107453L,
                                                                  Orientation.FORWARD, null, null, "batch");

        assertEquals("", subSnpCoreFields2.getReferenceInForwardStrand());
        assertEquals("C", subSnpCoreFields2.getAlternateInForwardStrand());
    }

    @Test
    public void hgvsCForwardHgvsTReverseStrandMustNotChange() throws Exception {
        SubSnpCoreFields subSnpCoreFields1 = new SubSnpCoreFields(26201546,
                                                                  Orientation.FORWARD, 13677177L,
                                                                  Orientation.FORWARD,
                                                                  "NT_455866.1",
                                                                  1766472L,
                                                                  1766472L,
                                                                  Orientation.FORWARD,
                                                                  LocusType.INSERTION,
                                                                  "4",
                                                                  91223961L,
                                                                  91223961L,
                                                                  "T", "T", "TAGA", "T/TAGA",
                                                                  "NC_006091.4:g.91223962insAGA", 91223961L, 91223961L,
                                                                  Orientation.FORWARD,
                                                                  "NT_455866.1:g.1766473insAGA", 1766472L, 1766472L,
                                                                  Orientation.REVERSE, null, null, "batch");

        assertEquals("T", subSnpCoreFields1.getReferenceInForwardStrand());
        assertEquals("TAGA", subSnpCoreFields1.getAlternateInForwardStrand());
    }

    @Test
    public void hgvsCNullHgvsTReverseStrandMustChange() throws Exception {
        SubSnpCoreFields subSnpCoreFields1 = new SubSnpCoreFields(26201546,
                                                                  Orientation.FORWARD, 13677177L,
                                                                  Orientation.FORWARD,
                                                                  "NT_455866.1",
                                                                  1766472L,
                                                                  1766472L,
                                                                  Orientation.FORWARD,
                                                                  LocusType.INSERTION,
                                                                  "4",
                                                                  91223961L,
                                                                  91223961L,
                                                                  "T", "T", "TAGA", "T/TAGA",
                                                                  null, null, null, Orientation.FORWARD,
                                                                  "NT_455866.1:g.1766473insAGA", 1766472L, 1766472L,
                                                                  Orientation.REVERSE, null, null, "batch");

        assertEquals("A", subSnpCoreFields1.getReferenceInForwardStrand());
        assertEquals("TCTA", subSnpCoreFields1.getAlternateInForwardStrand());
    }

    @Test
    public void allelesReverseStrandMustChange() throws Exception {
        assertEquals("T/C", buildSubSnpCoreFieldsWithOrientations("G/A", Orientation.REVERSE, Orientation.FORWARD,
                                                                  Orientation.FORWARD).getAllelesInForwardStrand());
        assertEquals("T/C", buildSubSnpCoreFieldsWithOrientations("G/A", Orientation.FORWARD, Orientation.REVERSE,
                                                                  Orientation.FORWARD).getAllelesInForwardStrand());
        assertEquals("T/C", buildSubSnpCoreFieldsWithOrientations("G/A", Orientation.FORWARD, Orientation.FORWARD,
                                                                  Orientation.REVERSE).getAllelesInForwardStrand());
        assertEquals("T/C", buildSubSnpCoreFieldsWithOrientations("G/A", Orientation.REVERSE, Orientation.REVERSE,
                                                                  Orientation.REVERSE).getAllelesInForwardStrand());
    }

    @Test
    public void allelesReverseStrandMustNotChange() throws Exception {
        assertEquals("T/C", buildSubSnpCoreFieldsWithOrientations("T/C", Orientation.FORWARD, Orientation.FORWARD,
                                                                  Orientation.FORWARD).getAllelesInForwardStrand());
        assertEquals("T/C", buildSubSnpCoreFieldsWithOrientations("T/C", Orientation.REVERSE, Orientation.REVERSE,
                                                                  Orientation.FORWARD).getAllelesInForwardStrand());
        assertEquals("T/C", buildSubSnpCoreFieldsWithOrientations("T/C", Orientation.FORWARD, Orientation.REVERSE,
                                                                  Orientation.REVERSE).getAllelesInForwardStrand());
        assertEquals("T/C", buildSubSnpCoreFieldsWithOrientations("T/C", Orientation.REVERSE, Orientation.FORWARD,
                                                                  Orientation.REVERSE).getAllelesInForwardStrand());
    }

    private SubSnpCoreFields buildSubSnpCoreFieldsWithOrientations(String alleles, Orientation subsnpOrientation,
                                                                   Orientation snpOrientation,
                                                                   Orientation contigOrientation) {
        return new SubSnpCoreFields(0, subsnpOrientation, 0L, snpOrientation,
                                    "", 0L, 0L, contigOrientation,
                                    LocusType.SNP, "", 0L, 0L,
                                    "", "", "", alleles,
                                    "", 0L, 0L, Orientation.FORWARD,
                                    "", 0L, 0L, Orientation.FORWARD, null, null, "batch");
    }

    @Test
    public void longAllelesReverseStrandMustChange() throws Exception {
        assertEquals("AGGG/TCC",
                     buildSubSnpCoreFieldsWithOrientations("GGA/CCCT", Orientation.REVERSE, Orientation.FORWARD,
                                                           Orientation.FORWARD).getAllelesInForwardStrand());
    }

    @Test
    public void emptyAlleles() throws Exception {
        assertEquals("AGGG/",
                     buildSubSnpCoreFieldsWithOrientations("-/CCCT", Orientation.REVERSE, Orientation.FORWARD,
                                                           Orientation.FORWARD).getAllelesInForwardStrand());
        assertEquals("/CCCT",
                     buildSubSnpCoreFieldsWithOrientations("-/CCCT", Orientation.FORWARD, Orientation.FORWARD,
                                                           Orientation.FORWARD).getAllelesInForwardStrand());
        assertEquals("//",
                     buildSubSnpCoreFieldsWithOrientations("-/-/-", Orientation.FORWARD, Orientation.FORWARD,
                                                           Orientation.FORWARD).getAllelesInForwardStrand());
        assertEquals("//",
                     buildSubSnpCoreFieldsWithOrientations("//", Orientation.FORWARD, Orientation.FORWARD,
                                                           Orientation.FORWARD).getAllelesInForwardStrand());
        assertEquals("/T/",
                     buildSubSnpCoreFieldsWithOrientations("/A/", Orientation.REVERSE, Orientation.FORWARD,
                                                           Orientation.FORWARD).getAllelesInForwardStrand());
        assertEquals("T///",
                     buildSubSnpCoreFieldsWithOrientations("/- /-/A", Orientation.REVERSE, Orientation.FORWARD,
                                                           Orientation.FORWARD).getAllelesInForwardStrand());
    }

    @Test
    public void trimmedAlleles() throws Exception {
        assertEquals("AGGG/AC",
                     buildSubSnpCoreFieldsWithOrientations("GT /CCCT", Orientation.REVERSE, Orientation.FORWARD,
                                                           Orientation.FORWARD).getAllelesInForwardStrand());
        assertEquals("GT/CCCT",
                     buildSubSnpCoreFieldsWithOrientations("GT /CCCT", Orientation.FORWARD, Orientation.FORWARD,
                                                           Orientation.FORWARD).getAllelesInForwardStrand());
        assertEquals("GT/CCCT",
                     buildSubSnpCoreFieldsWithOrientations("GT / CCCT ", Orientation.FORWARD, Orientation.FORWARD,
                                                           Orientation.FORWARD).getAllelesInForwardStrand());
        assertEquals("//",
                     buildSubSnpCoreFieldsWithOrientations(" / / ", Orientation.FORWARD, Orientation.FORWARD,
                                                           Orientation.FORWARD).getAllelesInForwardStrand());
        assertEquals("/T/",
                     buildSubSnpCoreFieldsWithOrientations("/A /", Orientation.REVERSE, Orientation.FORWARD,
                                                           Orientation.FORWARD).getAllelesInForwardStrand());
    }

    @Test
    public void secondaryAlternates() throws Exception {
        assertArrayEquals(new String[0],
                          buildSubSnpCoreFieldsWithAlleles("T", "A", "T/A", Orientation.FORWARD, Orientation.FORWARD,
                                                           Orientation.FORWARD).getSecondaryAlternatesInForwardStrand());
        assertArrayEquals(new String[]{"C"},
                          buildSubSnpCoreFieldsWithAlleles("T", "A", "T/A/C", Orientation.FORWARD, Orientation.FORWARD,
                                                           Orientation.FORWARD).getSecondaryAlternatesInForwardStrand());
        assertArrayEquals(new String[]{"A"},
                          buildSubSnpCoreFieldsWithAlleles("T", "G", "T/A/C", Orientation.REVERSE, Orientation.FORWARD,
                                                           Orientation.FORWARD).getSecondaryAlternatesInForwardStrand());
        assertArrayEquals(new String[]{"", "AA"},
                          buildSubSnpCoreFieldsWithAlleles("T", "GGG", "TT/A/CCC/-", Orientation.REVERSE, Orientation.FORWARD,
                                                           Orientation.FORWARD).getSecondaryAlternatesInForwardStrand());
        assertArrayEquals(new String[]{"", "AA"},
                          buildSubSnpCoreFieldsWithAlleles("T", "GGG", "TT/A/CCC/", Orientation.REVERSE, Orientation.FORWARD,
                                                           Orientation.FORWARD).getSecondaryAlternatesInForwardStrand());
    }

    private SubSnpCoreFields buildSubSnpCoreFieldsWithAlleles(String reference, String alternate, String alleles,
                                                              Orientation subsnpOrientation,
                                                              Orientation snpOrientation,
                                                              Orientation contigOrientation) {
        return new SubSnpCoreFields(0, subsnpOrientation, 0L, snpOrientation,
                                    "", 0L, 0L, contigOrientation,
                                    LocusType.SNP, "", 0L, 0L,
                                    reference, reference, alternate, alleles,
                                    "", 0L, 0L, Orientation.FORWARD,
                                    "", 0L, 0L, Orientation.FORWARD, null, null, "batch");
    }

    @Test
    public void getReferenceWithUndefinedHgvs() throws Exception {

        SubSnpCoreFields subSnpCoreFields = new SubSnpCoreFields(0, Orientation.FORWARD, 0L, Orientation.FORWARD,
                                                                 "", 0L, 0L, Orientation.FORWARD,
                                                                 LocusType.SNP, "", 0L, 0L,
                                                                 "", "", "", "",
                                                                 null, 0L, 0L, Orientation.FORWARD,
                                                                 null, 0L, 0L, Orientation.FORWARD,
                                                                 null, null, "batch");

        expectedException.expect(UndefinedHgvsAlleleException.class);
        subSnpCoreFields.getReferenceInForwardStrand();
    }

    @Test
    public void getAlternateWithUndefinedHgvs() throws Exception {

        SubSnpCoreFields subSnpCoreFields = new SubSnpCoreFields(0, Orientation.FORWARD, 0L, Orientation.FORWARD,
                                                                 "", 0L, 0L, Orientation.FORWARD,
                                                                 LocusType.SNP, "", 0L, 0L,
                                                                 "", "", "", "",
                                                                 null, 0L, 0L, Orientation.FORWARD,
                                                                 null, 0L, 0L, Orientation.FORWARD,
                                                                 null, null, "batch");

        expectedException.expect(UndefinedHgvsAlleleException.class);
        subSnpCoreFields.getAlternateInForwardStrand();
    }
}
