package com.altertech.evacc.ui.holders;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.altertech.evacc.AppConfig;
import com.altertech.evacc.R;
import com.altertech.evacc.core.config.Config;
import com.altertech.evacc.utils.ImageUtil;
import com.altertech.evacc.utils.StringUtil;
import com.altertech.evacc.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oshevchuk on 14.02.2019
 */
public class MenuHolder extends ContextWrapper {

    public enum Type {
        SETTINGS, RELOAD, EXIT, PAGE, ABOUT;
    }

    private CallBack listener;

    private View view;

    private Adapter adapter;

    private List<Menu> menu = new ArrayList<>();

    public MenuHolder(Context base, View view, CallBack listener) {
        super(base);
        this.view = view;
        this.listener = listener;
        this.init();
    }

    public MenuHolder setOrientation(int orientation) {
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
        } else {

        }
        this.view.findViewById(R.id.ui_f_menu_background).setBackgroundResource(orientation == Configuration.ORIENTATION_PORTRAIT ? R.drawable.background_menu_1 : R.drawable.background_menu_4);

        return this;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void init() {
        this.adapter = new Adapter(this.menu);
        RecyclerView view = this.view.findViewById(R.id.ui_f_menu_items);
        view.setAdapter(this.adapter);
        view.setHasFixedSize(true);
        view.setLayoutManager(new LinearLayoutManager(this));
    }


    public MenuHolder update(Config config) {
        this.menu.clear();
        this.menu.addAll(this.generate(config));
        this.adapter.notifyDataSetChanged();
        return this;
    }

    private List<Menu> generate(Config config) {

        List<Menu> menu = new ArrayList<>();

        menu.add(new Menu(Type.PAGE,
                getResources().getString(R.string.app_menu_item_name_home),
                config != null && StringUtil.isNotEmpty(config.getHome_icon()) ? ImageUtil.convert(config.getHome_icon()) : BitmapFactory.decodeResource(getResources(), R.drawable.drawable_menu_home),
                config != null && StringUtil.isNotEmpty(config.getIndex()) ? config.getIndex() : "/"));

        menu.addAll(this.getMenuFromConfig(config));

        if ((AppConfig.CONFIG != null && !AppConfig.CONFIG.isEnabled()) || AppConfig.AUTHENTICATION) {
            menu.add(new Menu(Type.SETTINGS,
                    getResources().getString(R.string.app_menu_item_name_settings),
                    BitmapFactory.decodeResource(getResources(), R.drawable.drawable_menu_setting)));
        }

        menu.add(new Menu(Type.RELOAD,
                getResources().getString(R.string.app_menu_item_name_reload),
                BitmapFactory.decodeResource(getResources(), R.drawable.drawable_menu_reload)));
        menu.add(new Menu(Type.ABOUT,
                getResources().getString(R.string.app_menu_item_name_about),
                BitmapFactory.decodeResource(getResources(), R.drawable.drawable_menu_info)));
        menu.add(new Menu(Type.EXIT,
                getResources().getString(R.string.app_menu_item_name_exit),
                BitmapFactory.decodeResource(getResources(), R.drawable.drawable_menu_exit)));

        return menu;
    }

    private List<Menu> getMenuFromConfig(Config config) {
        if (config == null || config.getMenu() == null || config.getMenu().isEmpty()) {
            return new ArrayList<>();
        } else {
            List<Menu> menu = new ArrayList<>();
            for (com.altertech.evacc.core.config.Menu m : config.getMenu()) {
                menu.add(new Menu(
                        Type.PAGE, m.getName(),
                        StringUtil.isNotEmpty(m.getIcon()) ? ImageUtil.convert(m.getIcon()) : BitmapFactory.decodeResource(getResources(), R.drawable.drawable_menu_page),
                        m.getUrl()));
            }
            return menu;
        }
    }

    private static class Menu {

        private Type type;

        private String name, url;

        private Bitmap icon;

        Menu(Type type, String name, Bitmap icon) {
            this.type = type;
            this.name = name;
            this.icon = icon;
        }

        Menu(Type type, String name, Bitmap icon, String url) {
            this(type, name, icon);
            this.url = url;
        }
    }

    public interface CallBack {
        void click(Type type);

        void click(Type type, String url);
    }

    private class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

        private List<Menu> menu;

        Adapter(List<Menu> menu) {
            this.menu = menu == null ? new ArrayList<>() : menu;
        }

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            return new Holder(LayoutInflater.from(MenuHolder.this).inflate(R.layout.ui_f_menu_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int i) {
            holder.ui_f_menu_item_icon.setImageBitmap(this.menu.get(i).icon);
            holder.ui_f_menu_item_name.setText(this.menu.get(i).name);

            ViewGroup.LayoutParams params = holder.ui_f_menu_item_shadow.getLayoutParams();
            if (i == 0) {
                holder.ui_f_menu_item_shadow.setBackgroundResource(R.drawable.background_menu_7);

                params.height = (int) MenuHolder.this.getResources().getDimension(R.dimen.space_75dp);
                holder.ui_f_menu_item_shadow.setLayoutParams(params);

                Utils.setMargins(holder.ui_f_menu_item_container, 0, (int) MenuHolder.this.getResources().getDimension(R.dimen.space_15dp), 0, (int) MenuHolder.this.getResources().getDimension(R.dimen.space_15dp));
            } else if (i == this.menu.size() - 1) {
                holder.ui_f_menu_item_shadow.setBackgroundResource(R.drawable.background_menu_5);

                params.height = (int) MenuHolder.this.getResources().getDimension(R.dimen.space_60dp);
                holder.ui_f_menu_item_shadow.setLayoutParams(params);

                Utils.setMargins(holder.ui_f_menu_item_container, 0, 0, 0, (int) MenuHolder.this.getResources().getDimension(R.dimen.space_15dp));
            } else {
                holder.ui_f_menu_item_shadow.setBackgroundResource(R.drawable.background_menu_6);

                params.height = (int) MenuHolder.this.getResources().getDimension(R.dimen.space_60dp);
                holder.ui_f_menu_item_shadow.setLayoutParams(params);

                Utils.setMargins(holder.ui_f_menu_item_container, 0, 0, 0, (int) MenuHolder.this.getResources().getDimension(R.dimen.space_15dp));
            }

            holder.itemView.setOnClickListener(v -> {
                if (menu.get(i).type.equals(Type.PAGE)) {
                    listener.click(/*dialog, */menu.get(i).type, menu.get(i).url);
                } else {
                    listener.click(/*dialog,*/ menu.get(i).type);
                }
            });
        }

        @Override
        public int getItemCount() {
            return this.menu.size();
        }

        class Holder extends RecyclerView.ViewHolder {

            private ImageView ui_f_menu_item_icon;
            private TextView ui_f_menu_item_name;
            private View ui_f_menu_item_shadow, ui_f_menu_item_container;

            Holder(@NonNull View itemView) {
                super(itemView);

                this.ui_f_menu_item_icon = itemView.findViewById(R.id.ui_f_menu_item_icon);
                this.ui_f_menu_item_name = itemView.findViewById(R.id.ui_f_menu_item_name);
                this.ui_f_menu_item_shadow = itemView.findViewById(R.id.ui_f_menu_item_shadow);
                this.ui_f_menu_item_container = itemView.findViewById(R.id.ui_f_menu_item_container);
            }
        }
    }

}
