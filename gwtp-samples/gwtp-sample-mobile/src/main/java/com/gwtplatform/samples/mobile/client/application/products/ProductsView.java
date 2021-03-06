/**
 * Copyright 2011 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.gwtplatform.samples.mobile.client.application.products;

import java.util.List;

import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.samples.mobile.client.application.product.ProductPresenter;
import com.gwtplatform.samples.mobile.client.place.NameTokens;
import com.gwtplatform.samples.mobile.shared.dispatch.Product;

public class ProductsView extends ViewImpl implements ProductsPresenter.MyView {
    public interface Binder extends UiBinder<Widget, ProductsView> {
    }

    @UiField
    Hyperlink backLink;
    @UiField
    FlowPanel productList;
    @UiField
    HeadingElement title;

    private final PlaceManager placeManager;

    @Inject
    public ProductsView(final Binder binder, final PlaceManager placeManager) {
        this.placeManager = placeManager;

        initWidget(binder.createAndBindUi(this));
    }

    @Override
    public void setBackLinkHistoryToken(String historyToken) {
        backLink.setTargetHistoryToken(historyToken);
    }

    @Override
    public void setList(List<Product> products) {
        productList.clear();
        for (Product product : products) {
            PlaceRequest request = new PlaceRequest(NameTokens.product).with(ProductPresenter.TOKEN_ID,
                    Integer.toString(product.getId()));
            productList.add(new Hyperlink(product.getName(), placeManager.buildRelativeHistoryToken(request)));
        }
    }

    @Override
    public void setMessage(String string) {
        productList.clear();
        productList.add(new Label(string));
    }

    @Override
    public void setTitle(String title) {
        this.title.setInnerHTML(title);
    }
}
