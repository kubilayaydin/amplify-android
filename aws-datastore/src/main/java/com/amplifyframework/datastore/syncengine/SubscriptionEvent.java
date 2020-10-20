/*
 * Copyright 2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.amplifyframework.datastore.syncengine;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;

import com.amplifyframework.api.graphql.SubscriptionType;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.datastore.appsync.ModelWithMetadata;

import java.util.Objects;

/**
 * A {@link SubscriptionEvent} notify the client of mutations that have
 * occurred on the backend.
 * @param <T> Type of data that has been mutated on the backend
 */
final class SubscriptionEvent<T extends Model> {
    private final ModelWithMetadata<T> modelWithMetadata;
    private final Class<T> modelClass;
    private final SubscriptionType type;

    private SubscriptionEvent(ModelWithMetadata<T> modelWithMetadata, Class<T> modelClass, SubscriptionType type) {
        this.modelWithMetadata = modelWithMetadata;
        this.modelClass = modelClass;
        this.type = type;
    }

    @NonNull
    ModelWithMetadata<T> modelWithMetadata() {
        return modelWithMetadata;
    }

    @SuppressWarnings("unused")
    @NonNull
    Class<T> modelClass() {
        return modelClass;
    }

    @SuppressWarnings("unused")
    @NonNull
    SubscriptionType type() {
        return type;
    }

    @NonNull
    static <T extends Model> Builder<T> builder() {
        return new Builder<>();
    }

    static final class Builder<T extends Model> {
        private ModelWithMetadata<T> modelWithMetadata;
        private Class<T> modelClass;
        private SubscriptionType type;

        @NonNull
        Builder<T> modelWithMetadata(@NonNull ModelWithMetadata<T> modelWithMetadata) {
            this.modelWithMetadata = Objects.requireNonNull(modelWithMetadata);
            return this;
        }

        @NonNull
        Builder<T> modelClass(@NonNull Class<T> modelClass) {
            this.modelClass = Objects.requireNonNull(modelClass);
            return this;
        }

        @NonNull
        Builder<T> type(@NonNull SubscriptionType type) {
            this.type = Objects.requireNonNull(type);
            return this;
        }

        @SuppressLint("SyntheticAccessor")
        @NonNull
        SubscriptionEvent<T> build() {
            return new SubscriptionEvent<>(modelWithMetadata, modelClass, type);
        }
    }
}
