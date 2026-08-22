/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.commons.data.xml.AbstractFileParser
 *  l2.commons.data.xml.AbstractHolder
 *  org.dom4j.Element
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package l2.gameserver.data.xml.parser.gf;

import java.io.File;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import l2.commons.data.xml.AbstractFileParser;
import l2.gameserver.data.xml.holder.gf.EssenceCountDownHolder;

public class EssenceCountDownParser
        extends AbstractFileParser<EssenceCountDownHolder> {
    protected static EssenceCountDownParser instance = new EssenceCountDownParser();
    protected static final Logger _log = LoggerFactory.getLogger(EssenceCountDownParser.class);

    protected EssenceCountDownParser() {
        super(EssenceCountDownHolder.getInstance());
    }

    public static EssenceCountDownParser getInstance() {
        return instance;
    }

    public File getXMLFile() {
        return new File("data/giran_forge/xml/custom_countdown.xml");
    }

    public String getDTDFileName() {
        return "";
    }

    protected void readData(Element root) throws Exception {
        for (Element element : root.elements("skill")) {
            int id = Integer.parseInt(element.attributeValue("id"));
            long reuse = Long.parseLong(element.attributeValue("reuse"));
            EssenceCountDownHolder.getInstance().addData(id, reuse);
        }
    }
}
