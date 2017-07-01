/*
 * TeleStax, Open Source Cloud Communications
 * Copyright 2011-2016, TeleStax Inc. and individual contributors
 * by the @authors tag.
 *
 * This program is free software: you can redistribute it and/or modify
 * under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 *
 * This file incorporates work covered by the following copyright and
 * permission notice:
 *
 *   JBoss, Home of Professional Open Source
 *   Copyright 2007-2011, Red Hat, Inc. and individual contributors
 *   by the @authors tag. See the copyright.txt in the distribution for a
 *   full listing of individual contributors.
 *
 *   This is free software; you can redistribute it and/or modify it
 *   under the terms of the GNU Lesser General Public License as
 *   published by the Free Software Foundation; either version 2.1 of
 *   the License, or (at your option) any later version.
 *
 *   This software is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *   Lesser General Public License for more details.
 *
 *   You should have received a copy of the GNU Lesser General Public
 *   License along with this software; if not, write to the Free
 *   Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 *   02110-1301 USA, or see the FSF site: http://www.fsf.org.
*/

package org.jdiameter.common.impl.app.slh;

import java.io.Serializable;

import org.jdiameter.api.Request;
import org.jdiameter.common.api.app.AppSessionDataLocalImpl;
import org.jdiameter.common.api.app.slh.ISLhSessionData;
import org.jdiameter.common.api.app.slh.SLhSessionState;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 *
 */

public class SLhLocalSessionDataImpl extends AppSessionDataLocalImpl implements ISLhSessionData {

  protected SLhSessionState state = SLhSessionState.IDLE;
  protected Request buffer;
  protected Serializable tsTimerId;

  public void setSLhSessionState(SLhSessionState state) {
    this.state = state;
  }

  public SLhSessionState getSLhSessionState() {
    return this.state;
  }

  public Serializable getTsTimerId() {
    return this.tsTimerId;
  }

  public void setTsTimerId(Serializable tid) {
    this.tsTimerId = tid;
  }

  public void setBuffer(Request buffer) {
    this.buffer = buffer;
  }

  public Request getBuffer() {
    return this.buffer;
  }

}