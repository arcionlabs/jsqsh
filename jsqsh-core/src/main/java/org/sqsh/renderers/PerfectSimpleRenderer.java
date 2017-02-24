/*
 * Copyright 2007-2017 Scott C. Gray
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sqsh.renderers;

import org.sqsh.RendererManager;
import org.sqsh.Session;

public class PerfectSimpleRenderer extends PerfectPrettyRenderer {

    public PerfectSimpleRenderer(Session session, RendererManager renderMan) {

        super(session, renderMan, false);
    }
}