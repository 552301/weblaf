/*
 * This file is part of WebLookAndFeel library.
 *
 * WebLookAndFeel library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * WebLookAndFeel library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with WebLookAndFeel library.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.alee.demo.api;

import com.alee.demo.skin.DemoStyles;
import com.alee.extended.label.WebStyledLabel;
import com.alee.extended.layout.HorizontalFlowLayout;
import com.alee.laf.separator.WebSeparator;
import com.alee.managers.style.StyleId;
import com.alee.managers.style.StyleManager;
import com.alee.managers.style.skin.Skin;

import javax.swing.*;
import java.util.List;

/**
 * Each {@link com.alee.demo.api.AbstractStylePreview} is tied to specific skin style.
 *
 * @author Mikle Garin
 */

public abstract class AbstractStylePreview extends AbstractPreview
{
    /**
     * Preview style ID.
     */
    protected final StyleId styleId;

    /**
     * Preview information.
     */
    protected JComponent previewInfo;

    /**
     * Preview content.
     */
    protected JComponent previewContent;

    /**
     * Constructs new style preview.
     *
     * @param example example this preview belongs to
     * @param id      preview ID
     * @param styleId preview style ID
     */
    public AbstractStylePreview ( final Example example, final String id, final StyleId styleId )
    {
        this ( example, id, FeatureState.common, styleId );
    }

    /**
     * Constructs new style preview.
     *
     * @param example example this preview belongs to
     * @param id      preview ID
     * @param state   feature state
     * @param styleId preview style ID
     */
    public AbstractStylePreview ( final Example example, final String id, final FeatureState state, final StyleId styleId )
    {
        super ( example, id, state );
        this.styleId = styleId;
    }

    /**
     * Returns preview style ID.
     *
     * @return preview style ID
     */
    protected StyleId getStyleId ()
    {
        return styleId;
    }

    @Override
    protected JComponent createPreview ( final List<Preview> previews, final int index )
    {
        final Preview preview = previews.get ( index );
        final HorizontalFlowLayout layout = new HorizontalFlowLayout ( 15, true );
        final PreviewPanel previewPanel = new PreviewPanel ( preview.getFeatureState (), layout );
        previewPanel.add ( getPreviewInfo () );
        previewPanel.add ( new WebSeparator ( DemoStyles.previewSeparator, WebSeparator.VERTICAL ) );
        previewPanel.add ( getPreviewContent () );
        return previewPanel;
    }

    @Override
    public JComponent getEqualizableWidthComponent ()
    {
        return getPreviewInfo ();
    }

    /**
     * Returns cached preview component information.
     *
     * @return cached preview component information
     */
    protected JComponent getPreviewInfo ()
    {
        if ( previewInfo == null )
        {
            previewInfo = createPreviewInfo ();
        }
        return previewInfo;
    }

    /**
     * Returns preview component information.
     *
     * @return preview component information
     */
    protected JComponent createPreviewInfo ()
    {
        return new WebStyledLabel ( DemoStyles.previewTitleLabel, getTitle () ).setBoldFont ();
    }

    /**
     * Returns cached preview content component.
     * This can be anything provided by the example.
     *
     * @return cached preview content component
     */
    protected JComponent getPreviewContent ()
    {
        if ( previewContent == null )
        {
            previewContent = createPreviewContent ( StyleId.panelTransparent );
        }
        return previewContent;
    }

    /**
     * Returns preview content component.
     * This can be anything provided by the example.
     *
     * @param id style ID
     * @return preview content component
     */
    protected abstract JComponent createPreviewContent ( StyleId id );

    @Override
    public void applySkin ( final Skin skin )
    {
        StyleManager.setSkin ( getPreviewContent (), skin, true );
    }
}