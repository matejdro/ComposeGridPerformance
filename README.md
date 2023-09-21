# ComposeGridPerformance

The goal of this project is to demonstrate performance issues related to Compose grid.

To achieve best possible performance examples are kept as simple as possible, also:
- verified in R8 release mode
- contains baseline profile for scrolling
- stable Compose parameters used
- unique and stable item keys applied for grid items


Currently these examples are implemented:
- network images grid with **Coil** AsyncImage loading
- paginated network images grid with **Coil** AsyncImage loading
- network images grid with **Glide** AsyncImage loading
- boxes with random colors grid
- paginated boxes with random colors grid
- modularized (2 static horizontal lists + paginated vertical list) boxes with random colors grid


## Images grid with **Coil** loading issues

Simple grid which instantly demonstrates that jank appears while scrolling the grid. Noticeable both by human eye and in profiler:
![jank_1](https://github.com/PauliusVal/ComposeGridPerformance/blob/main/images/images_grid_janky_frames_1.png?raw=true)

![jank_2](https://github.com/PauliusVal/ComposeGridPerformance/blob/main/images/images_grid_janky_frames_2.png?raw=true)

![jank_3](https://github.com/PauliusVal/ComposeGridPerformance/blob/main/images/images_grid_janky_frames_3.png?raw=true)

## Paginated images grid with **Coil** loading issues

Same issues as with simple image grid without pagination:
![jank_4](https://github.com/PauliusVal/ComposeGridPerformance/blob/main/images/paginated_images_grid_janky_frames_1.png?raw=true)

## Images grid with **Glide** loading issues

No noticeable performance issues though few janky frames are visible in profiler:
![jank_5](https://github.com/PauliusVal/ComposeGridPerformance/blob/main/images/glide_images_grid_janky_frames_1.png?raw=true)

## Colors grid issues

No performance issues visible even without a baseline profile.

## Paginated colors grid issues

No noticeable performance issues though janky frames are visible in profiler:
![jank_6](https://github.com/PauliusVal/ComposeGridPerformance/blob/main/images/paginated_colors_grid_janky_frames_1.png?raw=true)

## Modularized colors grid issues

Same janky frames as with regular paginated grid.